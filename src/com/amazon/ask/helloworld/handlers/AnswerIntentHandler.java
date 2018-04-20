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
         
   
         if (ans.equals("fact")){
        	 speechText = "Yes! You're right. Ketchup can indeed dissolve aluminium";
         }
         else{
        	 speechText = "Oops! That's the wrong answer! Ketchup can dissolve aluminium due to an electrochemical effect";
         }
        
       
        return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withShouldEndSession(false)
                    .build();
    }
}