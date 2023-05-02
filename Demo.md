[--Written by Nastaran Shekfote--]: _

# BPMN Auto Encapsulator Demo

In this document, I have explained the steps of automatically encapsulating a BPMN file with different intercepting options.

[----------------------------------------------------------------------------------------------------------------------------------------]: _
## Prerequisites

The following programs are needed to be installed on your system:
- IntelliJ IDEA 
- Java version 11
- Camunda modeler version 4.8.1. You can download it from [here](https://downloads.camunda.cloud/release/camunda-modeler/4.8.1/).

## Preparations

The following steps are needed before presenting the demo:

1. Open `BPMN-Auto-Encapsulator` project in IntelliJ by following steps:
   - 1.0) Navigate to the project directory by entering the following command in terminal:
   
     ```
     cd <`SeRDiWow-NastaranShekofte` repository-path>/Workspace/Java/BPMN-Auto-Encapsulator
     ```
   - 1.1) Right click on `pom.xml` and then select `Open-With-Other-Application -> IntelliJ IDEA Community Edition`. 

2. Open ATM case study in Camunda Modeler by following steps:
   - Find `ATM.bpmn` under `src/main/data` in the project panel.
   - Right click on the file and select `Open in -> Files`.
   - In the opened directory, right click on `ATM.bpmn` and select `Open With Camunda Modeler`.

## Demo of Automatically Encapsulating ATM Case Study with Internal Service Tasks

In this section, the steps for presenting a demo considering internal service tasks as interceptor are explained.

1. Open `config.properties` in the project panel, and replace its content with the following one:
   ```
   bpmn-file-path = src//main//data//ATM.bpmn
   aspect-file-path = src//main//data//aspects//javaClassServiceTask//ATM-aspect.xml
   woven-bpmn-file-path = src//main//data//outputs//woven-by-javaClassServiceTask.bpmn
   hook-type = JavaClassServiceTask
   ```

2. Run the project by clicking on `Run -> Run Main` from the toolbar. 
      - **Note:** in case you encountered errors related to `log4j`, right click on `Libs` in the project panel, then click on `Add as Library`, enter `Libs` as name, and click on `OK`.

3. After a successfull run, you will find the result of encapsulating ATM case study with Java class service task under `src/main/data/outputs/woven-by-javaClassServiceTask.bpmn`. Open it in Camunda modeler by following steps:
   - Right click on the file and select `Open in -> Files`.
   - In the opened directory, right click on `woven-by-javaClassServiceTask.bpmn` and select `Open With Camunda Modeler`.

[----------------------------------------------------------------------------------------------------------------------------------------]: _
## Demo of Automatically Encapsulating ATM Case Study with External Service Tasks

In this section, the steps for presenting a demo considering external service tasks as interceptor are explained.

1. Open `config.properties` in the project panel, and replace its content with the following one:
   ```
   bpmn-file-path = src//main//data//ATM.bpmn
   aspect-file-path = src//main//data//aspects//externalServiceTask//ATM-aspect.xml
   woven-bpmn-file-path = src//main//data//outputs//woven-by-externalServiceTask.bpmn
   hook-type = ExternalServiceTask
   ```

2. Run the project by clicking on `Run -> Run Main` from the toolbar. 
      - **Note:** in case you encountered errors related to `log4j`, right click on `Libs` in the project panel, then click on `Add as Library`, enter `Libs` as name, and click on `OK`.

3. After a successfull run, you will find the result of encapsulating ATM case study with Java class service task under `src/main/data/outputs/woven-by-externalServiceTask.bpmn`. Open it in Camunda modeler by following steps:
   - Right click on the file and select `Open in -> Files`.
   - In the opened directory, right click on `woven-by-externalServiceTask.bpmn` and select `Open With Camunda Modeler`.

[----------------------------------------------------------------------------------------------------------------------------------------]: _
## Demo of Automatically Encapsulating ATM Case Study with Execution Listeners

In this section, the steps for presenting a demo considering execution listeners as interceptor are explained.

1. Open `config.properties` in the project panel, and replace its content with the following one:
   ```
   bpmn-file-path = src//main//data//ATM.bpmn
   aspect-file-path = src//main//data//aspects//executionListener//ATM-aspect.xml
   woven-bpmn-file-path = src//main//data//outputs//woven-by-executionListener.bpmn
   hook-type = ExecutionListener
   ```

2. Run the project by clicking on `Run -> Run Main` from the toolbar. 
      - **Note:** in case you encountered errors related to `log4j`, right click on `Libs` in the project panel, then click on `Add as Library`, enter `Libs` as name, and click on `OK`.

3. After a successfull run, you will find the result of encapsulating ATM case study with Java class service task under `src/main/data/outputs/woven-by-executionListener.bpmn`. Open it in Camunda modeler by following steps:
   - Right click on the file and select `Open in -> Files`.
   - In the opened directory, right click on `woven-by-executionListener.bpmn` and select `Open With Camunda Modeler`.