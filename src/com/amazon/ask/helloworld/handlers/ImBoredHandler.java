package com.amazon.ask.helloworld.handlers;
 
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
 
import static com.amazon.ask.request.Predicates.intentName;
 
public class ImBoredHandler implements RequestHandler {
 
    public static final Map<Integer, String[]> questions = new HashMap<Integer, String[]>();
    static{
    		questions.put(0, new String[]{"Let's play Fact or Fiction. So basically , I am going to say a statement. You should say if it's a fact or just fiction. So here you go. Ketchup can dissolve aluminium."});
    		questions.put(1, new String[]{"<speak> Let's make a cheese sandwich today then. Are you ready? <break time='2s'/> Get two slices of bread </speak>", "<speak> Spread butter on the bread </speak>" ," <speak> Slice the correct amount of cheese for your sandwich </speak>","<speak> Place the two sides of bread together </speak>"});
    		questions.put(2, new String[]{"<speak> Let's make an omelette today then. <break time='2s'/> Add three eggs to a bowl and mix them</speak>","<speak> Heat the pan and put butter in it and add two thirds of the eggs in</speak>", "<speak> Sprinkle the cheese into the pan and add salt to taste</speak>", "<speak> Pour the remaining one third of the eggs over the omelette in the pan and cook for a few minutes</speak>"} );
 
    }
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ImBoredIntent"));
    }
 
    @Override
    public Optional<Response> handle(HandlerInput input) {
    	
   
        String question = (String) questions.get(0)[0];
        
       
        return input.getResponseBuilder()
                    .withSpeech(question)
                    .withShouldEndSession(false)
                    .build();
    }
}