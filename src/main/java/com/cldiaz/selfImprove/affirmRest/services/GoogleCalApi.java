package com.cldiaz.selfImprove.affirmRest.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cldiaz.selfImprove.affirmRest.models.AffirmResponse;
import com.cldiaz.selfImprove.affirmRest.models.Quote;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

@Service
public class GoogleCalApi {

	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "token";
    
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String calendarId = "primary";
    
    public GoogleCalApi() {}
    
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        
    	InputStream in = GoogleCalApi.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    
    private static Calendar getCalendar() throws GeneralSecurityException, IOException {
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        
        return service;
        
    }
    
    public List<Event> getEvents() throws IOException, GeneralSecurityException {
    	// Build a new authorized API client service.
       
        Calendar service = getCalendar(); 
        
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000);
      
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        
        List<Event> items = events.getItems();
        
        return items;
        
//        if (items.isEmpty()) {
//            System.out.println("No upcoming events found.");
//        } else {
//            System.out.println("Upcoming events");
//            for (Event event : items) {
//                DateTime start = event.getStart().getDateTime();
//                if (start == null) {
//                    start = event.getStart().getDate();
//                }
//                System.out.printf("%s (%s)\n", event.getSummary(), start);
//            }
//        }
    }
    
    public Boolean eventExists() throws IOException, GeneralSecurityException {
    	
         List<Event> items = getEvents();
         
         if(!items.isEmpty()) {
        	 return true;
         } else {
        	 return false;
         }
    }
    
    
    public String setDes_Calendar(Quote quote) {
    	
    	String result = quote.getQuote() + " -" + quote.getAuthor();
    	
    	return result;
    }
    
    public String createEvent(AffirmResponse affrim) throws GeneralSecurityException, IOException{
    	// Build a new authorized API client service.
        
    	Calendar service = getCalendar();
        
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        System.out.println("Date: " + formatter.format(today));
        
        Event affirmEvent = new Event()
        		.setSummary(affrim.getContents().getQuotes().get(0).getCategory() + " for today") 
        		.setDescription(setDes_Calendar(affrim.getContents().getQuotes().get(0)));
        
        EventDateTime start = new EventDateTime()
        		.setDate(new DateTime(formatter.format(today)));
        
        EventDateTime end = new EventDateTime()
        		.setDate(new DateTime(formatter.format(today)));

        affirmEvent.setStart(start)
        		   .setEnd(end);
        
        affirmEvent = service.events().insert(calendarId, affirmEvent).execute();
        
        //Log.debug("Event is created and HTML link is: " + affirmEvent.getHtmlLink());
        
        return affirmEvent.getHtmlLink();
        
    }
	
}
