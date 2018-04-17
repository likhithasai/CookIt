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
import com.amazon.ask.model.Response;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.s3.AmazonS3;

import java.util.Collections;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CookWithRecipeHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CanICookIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	GetItemResult response = AmazonDynamoDBClientBuilder.defaultClient()
    		.getItem(new GetItemRequest()
            .withTableName("items")
            .withKey(Collections.singletonMap("item_name", new AttributeValue().withS("chicken"))));
    	int qty = Integer.parseInt(response.getItem().get("item_qty").getN());
    	
    	
       String speechText = "Do you want to cook with" + String.valueOf(qty) + "chicken?";
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CanICook", speechText)
                .build();
    }

}

