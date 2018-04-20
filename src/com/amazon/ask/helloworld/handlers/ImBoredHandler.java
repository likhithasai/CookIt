package com.amazon.ask.helloworld.handlers;
 
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.amazon.ask.request.Predicates.intentName;
 
public class ImBoredHandler implements RequestHandler {
 
    public static final Map<Integer, String[]> questions = new HashMap<Integer, String[]>();
    static{
    		questions.put(0, new String[]{"Let's play Food Fact or Fiction. So basically , I am going to say a statement. You should say if it's a fact or just fiction. So here you go. Ketchup can dissolve aluminium."});
    		questions.put(1, new String[]{"Let's play Food Fact or Fiction. So basically , I am going to say a statement. You should say if it's a fact or just fiction. So here you go. There's a place called popcorn capital of the world."});
    		questions.put(2, new String[]{"Let's play Food Fact or Fiction. So basically , I am going to say a statement. You should say if it's a fact or just fiction. So here you go. Apple trees belong to the rose family."});
    		questions.put(3, new String[]{"Let's play Food Fact or Fiction. So basically , I am going to say a statement. You should say if it's a fact or just fiction. So here you go. It takes seven years to digest chewing gum."});
    		questions.put(4, new String[]{"Let's play Food Fact or Fiction. So basically , I am going to say a statement. You should say if it's a fact or just fiction. So here you go. Celery has negative calories."});
 
    }
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ImBoredIntent"));
    }
 
    @Override
    public Optional<Response> handle(HandlerInput input) {
    	Random r = new Random();
		int idx = (int) r.nextInt(5);
		System.out.println(idx);
		input.getAttributesManager().getSessionAttributes().put("currentQuestion", idx);
        String question = (String) questions.get(idx)[0];
        
       
        return input.getResponseBuilder()
                    .withSpeech(question)
                    .withShouldEndSession(false)
                    .build();
    }
}