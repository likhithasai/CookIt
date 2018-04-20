package com.amazon.ask.helloworld.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.Request;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class DoesItContainHandler implements RequestHandler {
    public static final Map<String, ArrayList<String>> ings = new HashMap<String, ArrayList<String>>();
    public static final ArrayList<String> csf = new ArrayList<String>();
    public static final ArrayList<String> om = new ArrayList<String>();
    public static final ArrayList<String> cs = new ArrayList<String>();
    
    
    
    static{
    	
        csf.add("olive oil");
        csf.add("garlic");
        csf.add("chicken");
        csf.add("red pepper");
        csf.add("soy sauce");
        csf.add("mangetout");
        csf.add("noodles");
        csf.add("nuts");
        
        om.add("eggs");
        om.add("butter");
        om.add("cheese");
        
        cs.add("cheese");
        cs.add("bread");
        cs.add("butter");
        

   		ings.put("Chicken Stir Fry",csf);
   		ings.put("Cheese Sandwich", om);
   		ings.put("Omelette",cs);
    }
       
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("DoesItContainIntent"));
    }


    @Override
    public Optional<Response> handle(HandlerInput input) {

	    Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

          // Get the color slot from the list of slots.
        Slot firstingSlot = slots.get("ing_con");
       
        String ing1 = firstingSlot.getValue();
        String speechText = "";

        String recipe = (String) input.getAttributesManager().getSessionAttributes().get("currentRecipe");

        if (recipe.equals("Chicken Stir Fry")){
        	if (ings.get("Chicken Stir Fry").contains(ing1)){
        		speechText = "Yes this recipe contains " + ing1;
        	}
        	else{
        		speechText = "No this recipe does not contain "+ ing1;
        	}
        }
        else if(recipe.equals("Omelette")){
        	if (ings.get("Omelette").contains(ing1)){
        		speechText = "Yes this recipe contains " + ing1;
        	}
        	else{
        		speechText = "No this recipe does not contain "+ ing1;
        	}

        }
        else if(recipe.equals("Cheese Sandwich")){
        	if (ings.get("Cheese Sandwich").contains(ing1)){
        		speechText = "Yes this recipe contains " + ing1;
        	}
        	else{
        		speechText = "No this recipe does not contain "+ ing1;
        	}
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CanICook", speechText)
                .withShouldEndSession(false)
                .build();
    }


}