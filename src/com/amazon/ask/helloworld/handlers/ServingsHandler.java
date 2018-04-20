
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

public class ServingsHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ServingsIntent"));
    }


    @Override
    public Optional<Response> handle(HandlerInput input) {
   
       String speechText = "";
       
       String recipe = (String) input.getAttributesManager().getSessionAttributes().get("currentRecipe");
       
       if (recipe.equals("Chicken Stir Fry")){
    	   		
    	   		speechText = "four";
    	   	
    	   		
       }
       else if(recipe.equals("Omelette")){
	    	    
		   	speechText = "one";
		   		
		   	
       }
       else if(recipe.equals("Cheese Sandwich")){
	    	   
		    speechText = "one";	
       }
       
       speechText = "The recipe serves "+ speechText;
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CanICook", speechText)
                .withShouldEndSession(false)
                .build();
    }

    
}
