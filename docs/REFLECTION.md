# Reflections Log

This document serves as a log of reflections on various topics, capturing insights, lessons learned, and personal 
growth. It is intended to help users track their thoughts and experiences over time.

Reflection Questions:
AI Effectiveness: Where did AI tools excel? Where did they struggle?
Throughout the entire project ChatGPT gave me only 2 issues, and one of them was my fault. One issue was due to me 
referreing to Shape3D as Shape3d in the prompt which created errors in the code. Luckily I was able to refactor to the
correct Class name which allowed my code to run. 


Code Quality Comparison: How does AI-generated code compare to manual coding?
It is very nice being able to create code for multiple classes with a single prompt. Multiple times throughout the reflection
I've stated that taking time on the prompt helped me avoid errors and communicate exactly what I need. The code quality is much
better and I can learn from analyzing the code it generates. It's much quicker than writing it myself.


Learning Experience: What did you learn about inheritance AND AI-assisted development?
AI assisted development is going to save me a lot of time and effort and act as a learning tool for the future. In this project
I learned how interfaces and classes are used together to create a program which I wasn't exposed to in my first Java class.
If you tell The Ai the classes you want to make and what it extends, it will understand the hierarchy and relationships between the classes 
which makes further additions to the code easy too.

Validation Process: How did you ensure the AI-generated code was correct?
I generated multiple objects with various values to ensure the calculations were correct. I also cross-checked the formulas with
the ones provided in the README.md file and the internet. I ran tests on the classes individually and then as the whole package with no
issues arising. 


Future Applications: How will you use AI tools in future programming projects?
I Will use AI tools in the future to create groups of related classes to save me time ane effort. I can also analyze the code it generates
and learn better coding habits and techniques.




1st Reflection:
I used ChatGPT to generate the Shape3D class, and it copied into the IDE with a weird error. The
IDE didn't like the Shape3D class name, and suggested it be named Shape3D? I enabled the change and
the error disappeared. Other than that I believe the code was generated how I needed it to.




2nd Reflection:
I figured out that the class name issue I had yesterday was because the class name was 
"Shape3D" while the subclasses extended "shape3d" so I refactored and let technology fix the 
classes for me. Using AI has been a great experience so far and I am looking forward to it 
saving me more time and energy. Being specific in my prompts has helped me avoid errors in 
the software so far.




3rd Reflection:
I created the test classes for each of the shape classes and I ran into no issues. I looked at 
what we needed the test classes to do and researched what I didn't know, communicated that to chatGPT in a clear 
and specific manner and was able to create all test classes using one prompt. After inserting the test classes into 
the project I used chatGPT as a refresher for how to run test. I decided to test each class individually and then
followed-up by right-clicking the java directory within test and selecting "Run 'All Tests" to make sure the whole 
project passed in one test. There wasn't a single error, and I am confident the program will run after I add to the 
driver class.





4th Reflection: 
I created the Driver class with ChatGPT and I am very pleased with the results. The only issue I had with the original 
code was that it reference Shape3D as Shape3d (a mistake I fixed earlier), so I need to communicate that to my AI so 
it knows for the future. This was a great learning experience. Next I plan on  