/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.helloworld.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.Request;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.s3.AmazonS3;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CanICookHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CanICookIntent"));
    }


    @Override
    public Optional<Response> handle(HandlerInput input) {
       Request request = input.getRequestEnvelope().getRequest();
       IntentRequest intentRequest = (IntentRequest) request;
       Intent intent = intentRequest.getIntent();
       Map<String, Slot> slots = intent.getSlots();

        // Get the color slot from the list of slots.
       Slot firstingSlot = slots.get("ing");
       String ing1 = firstingSlot.getValue();
       String speechText = "";
       if (ing1.equals("chicken")){
    	   		String[] steps = NextStepHandler.recipes.get("Chicken Stir Fry");
    	   		speechText = steps[0];
    	   		input.getAttributesManager().getSessionAttributes().put("currentStep", 1);
    	   		input.getAttributesManager().getSessionAttributes().put("currentRecipe", "Chicken Stir Fry");
    	   		System.out.println(input.getAttributesManager().getSessionAttributes().get("currentRecipe"));
    	   		System.out.println(input.getAttributesManager().getSessionAttributes().get("currentStep"));
    	   		
       }
       else if(ing1.equals("eggs")){
    	   		speechText = "Cook with eggs";
       }
    
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CanICook", speechText)
                .withShouldEndSession(false)
                .build();
    }

    
}
