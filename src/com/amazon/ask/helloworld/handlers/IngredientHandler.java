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

public class IngredientHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("IngredientIntent"));
    }


    @Override
    public Optional<Response> handle(HandlerInput input) {

        String speechText = "";

        String recipe = (String) input.getAttributesManager().getSessionAttributes().get("currentRecipe");

        if (recipe.equals("Chicken Stir Fry")){

            speechText = "1 tablespoon of olive oil, 1 garlic clove, 2 chicken thighs, half a red pepper, 1 tablespoon of soy sauce, 30 grams of mangetout, 100 millilitres of chicken stock and 1 nest of egg noodles";


        }
        else if(recipe.equals("Omelette")){

            speechText = "2 large eggs, 1 small knob of unsalted butter and 1 small handful of grated cheese";


        }
        else if(recipe.equals("Cheese Sandwich")){

            speechText = "2 slices of brown or white bread, 2 slices of cheese and 1 small knob of unsalted butter";
        }

        speechText = "The ingredients of this recipe are "+ speechText;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CanICook", speechText)
                .withShouldEndSession(false)
                .build();
    }


}