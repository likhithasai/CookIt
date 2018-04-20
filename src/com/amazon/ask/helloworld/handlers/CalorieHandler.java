
package com.amazon.ask.helloworld.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.Request;


import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CalorieHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CalorieIntent"));
    }


    @Override
    public Optional<Response> handle(HandlerInput input) {
   
       String speechText = "";
       
       String recipe = (String) input.getAttributesManager().getSessionAttributes().get("currentRecipe");
       
       if (recipe.equals("Chicken Stir Fry")){
    	   		
    	   		speechText = "six hundred calories";
    	   	
    	   		
       }
       else if(recipe.equals("Omelette")){
	    	    
		   	speechText = "one hundred and fifty calories";
		   		
		   	
       }
       else if(recipe.equals("Cheese Sandwich")){
	    	   
		    speechText = "four hundred calories";	
       }
       
       speechText = "The recipe contains "+ speechText;
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CanICook", speechText)
                .withShouldEndSession(false)
                .build();
    }

    
}
