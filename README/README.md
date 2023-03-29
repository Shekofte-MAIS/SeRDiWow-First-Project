This project was developed as part of my `1st-Paper-Size-Project` to intercept the monitoring-needed elements of a workflow.
It also consists part of enforcing mechanism.

In order to use the project, follow these steps:
1. Set the following properties in the [config.properties](/src/main/resources/config.properties) file.
   - `bpmn-file-path`: the path to the original bpmn file.
   - `aspect-file-path`: the path to the aspect file.
   - `woven-bpmn-file-path`: the path in which we want to store the output, which is the woven bpmn file.
   - `hook-type`: type of the interceptor module which acts like a hook. See the project proposal for more details.
     - Valid options for this attribute are: `ExecutionListener`, `ExternalServiceTask`, and `JavaClassServiceTask`.

2. Put the original bpmn file in `bpmn-file-path`.

3. Prepare the aspect file in XML format, in `aspect-file-path`. The structure of the aspect file depends on the hook type.
   The aspect XML tree structure corresponding each hook type is shown in the following figure.

   <figure>
      <img width="730" src="/home/shekofte/Documents/Research/Projects-Workspace/SeRDiWow-First-Project/README/aspect-files-xml-tress-structures.png"
           alt="Aspect files XML tree structures"/>
      <figcaption>Aspect files XML tree structures</figcaption>
   </figure>

4. Simply run the project. You can see the result of weaving in `woven-bpmn-file-path`. 

**NOTE:** the aspect file is just related to the interceptor part. There is also an enforcer part which is
woven into the bpmn file. But this enforcer part is always fixed, which contains mainly an `Email error message` 
service task as well as an end event.