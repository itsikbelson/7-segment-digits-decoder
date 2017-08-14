# Invoice Numbers Parser
This program reads from a file invoice numbers represented using 7-segments-display digits.
The numbers are being parsed to the actual value.

## Build
In order to build the application, run:
`./gradlew clean build`

## Run
In order to run the application, run:<br>
`java -jar build/libs/invoice-numbers-parser.jar <input-file-name> <output-file-name>`, where:
* input-file-name is the file to parse
* output-file-name is the output file name

Examples:
* `java -jar build/libs/invoice-numbers-parser.jar build/resources/test/q1a-valid/input_Q1a.txt build/resources/test/q1a-valid/ouput.txt`
* `java -jar build/libs/invoice-numbers-parser.jar build/resources/test/q1b-illegal/input_Q1b.txt build/resources/test/q1b-illegal/ouput.txt`

