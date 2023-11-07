# CS5319-Final-Project-Group-16-Andrew-Daiuto
# How to Run
This program was created in Java using JetBrains IntelliJ IDEA (https://www.jetbrains.com/idea/) and utilizes JavaFX/FXML to construct the user interface.
To run this program:
1. Clone this repository to your machine using `git clone https://github.com/adaiuto1/CS5319-Final-Project-Group-16-Andrew-Daiuto`
2. Open IntelliJ and click File > Open
3. Navigate to the repository folder and click on the "dev/demo" folder. ![alt text](https://i.imgur.com/zcfrtU6.png)
4. Once the directory is opened, click Edit Configurations ![image](https://github.com/adaiuto1/CS5319-Final-Project-Group-16-Andrew-Daiuto/assets/78216021/a9375cf2-baaf-4082-8661-ac2dc911bd03)
5. Add a new configuration with Java JDK 17 (install if necessary https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ![image](https://github.com/adaiuto1/CS5319-Final-Project-Group-16-Andrew-Daiuto/assets/78216021/610c62a6-84bb-44e0-aa51-ad122f1895da)
6. Click Apply and run the program with the Green Arrow at the top.
# Architecture
Unitype was created using an event-based architecture. This seemed to be the best option for a system that returns live feedback about user input. The main benefit of using an event-based architecture is that different types of inputs can be processed individually. The typing interface relies on three types of inputs:
1. Clock Ticks (Seconds)
2. Key Strokes
3. Spacebar Strokes  
Beyond these user inputs, other values are returned by system components such as:  
1. Correct Spelling
2. Words Typed
3. Accuracy
4. Typing Speed  
Since each of these values are calculated independently, the system need not update global variables or notify the entire system about changes in individual values. By comparison, the layered architecture handles the timeline of input handling differently. The layered architecture comprises an Input layer, a Processing layer, and a Display layer. In my program, the input layer would only be responsible for fetching prompts and gathering user text input. This means there is only one type of data (text inputs) that can be passed from the Input layer to the Processing layer. The Processing layer would then be left alone to calculate each value in response to every keystroke. This is an inefficient use of resources as not every component is only concerned with key stroke inputs. The event-based architecture provides a  separation of concerns between components and allows them to communicate directly using the event bus in a publish/subscribe format. Specifically, the NewTick signal from the Clock component triggers a separate set of events in the AccuracyCheck and SpeedCheck components that couldn't be calculated with text inputs alone, as would be the case in a layered architecture. In short, the amount of communication done passively between components as the program is running makes it ideal for an event-based architecture rather than layered.
