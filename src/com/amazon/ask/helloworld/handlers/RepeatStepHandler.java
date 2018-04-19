package com.amazon.ask.helloworld.handlers;
 
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
 
import static com.amazon.ask.request.Predicates.intentName;
 
public class RepeatStepHandler implements RequestHandler {
 
    public static final Map<String, String[]> recipes = new HashMap<String, String[]>();
    static{
    		recipes.put("Chicken Stir Fry", new String[]{"<speak> Let's make a chicken stir fry today then. Are you ready? <break time='2s'/> Heat the oil in a large frying pan or wok and fry the garlic and chicken, until browned all over.</speak>","<speak> Add the pepper to the pan and stir-fry for 2-3 minutes. Stir in the soy sauce, mangetout and chicken stock. Continue to stir-fry over a medium-high heat for 3-4 minutes, or until the chicken is cooked through. Add in the cooked noodles and stir to combine </speak>"});
    		recipes.put("Cheese Sandwich", new String[]{"<speak> Let's make a cheese sandwich today then. Are you ready? <break time='2s'/> Get two slices of bread </speak>", "<speak> Spread butter on the bread </speak>", "<speak> Slice the correct amount of cheese for your sandwich </speak>", "<speak> Place the two sides of bread together </speak>"});
    		recipes.put("Omelette", new String[]{"<speak> Let's make an omelette today then. <break time='2s'/> Add three eggs to a bowl and mix them</speak>","<speak>Heat the pan and put butter in it and add two thirds of the eggs in</speak>", "<speak> Sprinkle the cheese into the pan and add salt to taste</speak>", "<speak>Pour the remaining one third of the eggs over the omelette in the pan and cook for a few minutes</speak>"} );
    }
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RepeatStepIntent"));
    }
 
    @Override
    public Optional<Response> handle(HandlerInput input) {
    	
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        int step = (int) sessionAttributes.get("currentStep");
        String recipe = (String) sessionAttributes.get("currentRecipe");
        String speechText = "";
      
        	speechText = recipes.get(recipe)[step - 1];     	   
        
       
        return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withShouldEndSession(false)
                    .build();
    }
}