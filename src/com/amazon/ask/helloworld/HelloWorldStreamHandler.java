/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.helloworld;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.helloworld.handlers.CancelandStopIntentHandler;
import com.amazon.ask.helloworld.handlers.CanICookHandler;
import com.amazon.ask.helloworld.handlers.CookWithRecipeHandler;
import com.amazon.ask.helloworld.handlers.HelpIntentHandler;
import com.amazon.ask.helloworld.handlers.SessionEndedRequestHandler;
import com.amazon.ask.helloworld.handlers.LaunchRequestHandler;
import com.amazon.ask.helloworld.handlers.NextStepHandler;
import com.amazon.ask.helloworld.handlers.RepeatStepHandler;
import com.amazon.ask.helloworld.handlers.ServingsHandler;
import com.amazon.ask.helloworld.handlers.CalorieHandler;
import com.amazon.ask.helloworld.handlers.TimeHandler;
import com.amazon.ask.helloworld.handlers.ServingsHandler;
import com.amazon.ask.helloworld.handlers.ImBoredHandler;
import com.amazon.ask.helloworld.handlers.AnswerIntentHandler;
import com.amazon.ask.helloworld.handlers.IngredientHandler;

public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new CanICookHandler(),
                        new CookWithRecipeHandler(),
                        new NextStepHandler(),
                        new RepeatStepHandler(),
                        new CalorieHandler(),
                        new ServingsHandler(),
                        new ImBoredHandler(),
                        new AnswerIntentHandler(),
                        new IngredientHandler(),
                        new TimeHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler())
                .withSkillId("amzn1.ask.skill.2fc0643f-047d-4202-8b97-fdad695e09ac")
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}
