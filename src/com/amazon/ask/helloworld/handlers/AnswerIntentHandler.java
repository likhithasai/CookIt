package com.amazon.ask.helloworld.handlers;
 
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
 
import static com.amazon.ask.request.Predicates.intentName;
 
public class AnswerIntentHandler implements RequestHandler {
 

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AnswerIntent"));
    }
 
    @Override
    public Optional<Response> handle(HandlerInput input) {
    	 Request request = input.getRequestEnvelope().getRequest();
         IntentRequest intentRequest = (IntentRequest) request;
         Intent intent = intentRequest.getIntent();
         Map<String, Slot> slots = intent.getSlots();

         // Get the color slot from the list of slots.
         Slot firstingSlot = slots.get("ans");
         
         String ans = firstingSlot.getValue();
         String speechText = "";
         
         int qn_idx = (int) input.getAttributesManager().getSessionAttributes().get("currentQuestion");
         if (qn_idx == 0){
	        	 if (ans.equals("fact")){
	            	 speechText = "Yes! You're right. Ketchup can indeed dissolve aluminium";
	         }
	         else{
	            	 speechText = "Oops! That's the wrong answer! Ketchup can dissolve aluminium due to an electrochemical effect";
	         }
         }
         else if (qn_idx == 1){
         	 if (ans.equals("fact")){
         		 speechText = "Yes! You're right. Marion, Ohio, in the United States calls itself the popcorn capital of the world of the world.";
         	 }
         	 else{
         		 speechText = "Oops! That's the wrong answer! Marion, Ohio, in the United States calls itself the popcorn capital of the world of the world";
         	 }
         }
         else if (qn_idx == 2){
         	 if (ans.equals("fact")){
         		 speechText = "Yes! You're right. Apple trees do belong to the rose family";
         	 }
         	 else{
         		 speechText = "Oops! That's the wrong answer! Apple trees do belong to the rose family. The rose family also include fruits like cherries and strawberries";
         	 }
         }
         else if (qn_idx == 3){
         	 if (ans.equals("fact")){
         		 speechText = "Oops! That's the wrong answer! Some components of it don't breakdown and just pass through you undisturbed. ";
         	 }
         	 else{
         		 speechText = "Yes you're right! That's fiction but don't try this at home";
         	 }
         }
         else if (qn_idx == 4){
        	     if (ans.equals("fact")){
        	    	 	 speechText = "Oops! That's the wrong answer! A stick of celery has 10 calories but takes only 3 calories to digest.";
	         }
        	     else{
        	    	 	 speechText = "Yes you're right! That's fiction";
        	     }
         }
        
       
        return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withShouldEndSession(false)
                    .build();
    }
}