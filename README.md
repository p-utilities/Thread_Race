# ThreadRace

   The point of this app is to visually represent the use of semaphores in multithreading to the students and anyone interested in how semaphores work. When the button start is pressed, if barriers(semaphores) are on, the cars will go to the barriers and wait for other cars to arrive. Only when all cars are in front of the barrier, the cars can go through. If barriers are off, cars will go around the track changing speed on start an finish line. All cars work with random calculation so that the speed of each car is different every time. 

  This project is done with MVC. The controller is used for communication between model and view. This way, the model doesn't know about the view and view doesn't know about the model. In this way, I tried to make every part of my code reusable. 

  In the class Race in package model, inside method stopRace, I used the deprecated method Thread.stop . This is because I tried many other implementations but there is a problem with semaphores. The problem is that when threads are not stopped, they tend to, despite interrupt is called, continue to wait inside the semaphore. When threads are stopped by the deprecated method, semaphores are cleaned correctly.

About the view :
----------------
   The view is made from one simple JFrame with JPanels for heading and for the track. Below the heading, two buttons are added, one for starting and stopping the race, and another for setting barriers on and off. Initially, barriers are set to on. When buttons are pressed, the background of the buttons are changed, from start to stop, and from barriers off to barriers on. When the button stop is pressed, cars are set to their pol positions on a start line. When barriers off button are pressed, pit line is set to not visible, start line and finish line are left to be visible but they have no role if barriers are off except for good look and feel. Cars are made of JLabel and have a special class for them self.
  
About the model :
-----------------
   Model is made from two packages, package model and package model.mySemaphores . In the package model, class Racer is the runnable, and class Race is the controller for five Runnable racers. Semaphores are made to be a singleton, there are three semaphores: start semaphore, pit semaphore and finish semaphore. Every racer can have all three, or just two semaphores. Start and finish semaphore are crucial. 
   
About controller :
------------------
   The controller is used to be a communication center between model and view. An object of this class is also the starting point of the GUI. It initializes JFrame. In mainApp#ThreadRacerMain, an object of this class is created inside the main method.
