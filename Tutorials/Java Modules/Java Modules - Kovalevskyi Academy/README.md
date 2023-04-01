<h1>Command Overview</h1>

Compile and run SimpleModule:<br>
`find ./SimpleModule/ -name "*.java" | xargs javac -verbose -d out/production/SimpleModule`<br>
`java -p out/production/ -m SimpleModule/sample.example.SimpleModule`<br>

Compile and run CalculatorAPI:<br>
`find ./CalculatorAPI/ -name "*.java" | xargs javac -verbose -p out/production/ -d out/production/CalculatorAPI/`<br>
`java -p out/production/ -m CalculatorAPI/com.CalculatorTest`<br>

Compile and run CalcIMPL:<br>
`find ./CalcIMPL/ -name "*.java" | xargs javac -verbose -p out/production/ -d out/production/CalcIMPL/`<br>
`java -p out/production/ -m CalcIMPL/implementation.TestThisImplementation`<br>

Compile and run Beginner:<br>
`find ./Beginner/ -name "*.java" | xargs javac -verbose -p out/production/ -d out/production/Beginner/`<br>
`java -p out/production/ -m Beginner/com.start.Main`<br>

Build JAR: SimpleModule<br>
`cd out/production/SimpleModule/ && find -name "*.class" | xargs jar --verbose --create --file executed.jar --main-class sample.example.SimpleModule`<br>
`mkdir -p out/artifacts/ && mv -uv out/production/SimpleModule/executed.jar out/artifacts/executed.jar`<br>

Build JRE:<br>
`jlink -v -p "out\production\" --add-modules "SimpleModule" --output myJRE` - (Windows command)<br>

Run JRE:<br>
`.\myJRE\bin\java -m SimpleModule/sample.example.SimpleModule` - (Windows command)<br>
`du -hs myJRE` - Show JRE size<br>

<br><br>
<h4>Interesting facts that I found during the tutorial</h4>
1. We can't create package with "default" name.