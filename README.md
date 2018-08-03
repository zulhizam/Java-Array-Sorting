# jpmTest

This is an Test Project.

See the [POM file](https://github.com/zulhizam/jpmTest/blob/master/pom.xml)
for how the downloads plugin and site plugin are configured.

# Getting started

```
$ git clone https://github.com/zulhizam/jpmTest.git
$ mvn clean install
```
# Running the Apps
```
$ mvn compile
$ mvn clean package
$ java -cp target/com.jp.test-0.0.1-SNAPSHOT.jar com.jp.test.App
```
# Expected Results
```
Entity Incoming Rank (USD)
==========================

1. sef: 275000.0
2. bar: 32974.5
3. foo: 10025.0

Entity Outgoing Rank (USD)
==========================

1. foo: 10025.0
2. sef: 2750.0
3. bar: 472.5
--------------------------



Daily Incoming Rank (USD)
==========================

1. 13 Jun 2016: 275000.0
2. 08 Feb 2016: 18075.0
3. 07 Jan 2016: 14899.5
4. 04 Jan 2016: 10025.0

Daily Outgoing Rank (USD)
==========================

1. 04 Jan 2016: 12775.0
2. 07 Jan 2016: 472.5
3. 13 Jun 2016: 0.0
4. 08 Feb 2016: 0.0
--------------------------
```
<h3>Sample data represents the instructions sent by various clients to JP Morgan to execute in the international market. </h3>
<table>
    <tr>
        <td>Entity</td>
        <td>Buy/Sell</td>
        <td>AgreedFx</td>
        <td>Currency</td>
        <td>InstructionDate</td>
        <td>SettlementDate</td>
        <td>Units</td>
        <td>Price per unit</td>
    </tr>
    <tr>    <td>foo</td>
            <td>B</td>
            <td>0.50</td>
            <td>SGP</td>
            <td>01 Jan 2016</td>
            <td>02 Jan 2016</td>
            <td>200</td>
            <td>100.25</td>
    </tr>
    <tr>    <td>foo</td>
            <td>S</td>
            <td>0.50</td>
            <td>SGP</td>
            <td>01 Jan 2016</td>
            <td>02 Jan 2016</td>
            <td>200</td>
            <td>100.25</td>
    </tr>
    <tr>
            <td>bar</td>
            <td>S</td>
            <td>0.22</td>
            <td>AED</td>
            <td>05 Jan 2016</td>
            <td>07 Jan 2016</td>
            <td>450</td>
            <td>150.5</td>
    </tr>
    <tr>
            <td>bar</td>
            <td>B</td>
            <td>0.18</td>
            <td>SAR</td>
            <td>05 Jan 2016</td>
            <td>07 Jan 2016</td>
            <td>250</td>
            <td>10.5</td>
    </tr>
    <tr>
            <td>bar</td>
            <td>S</td>
            <td>0.50</td>
            <td>SGD</td>
            <td>05 Feb 2016</td>
            <td>07 Feb 2016</td>
            <td>300</td>
            <td>120.5</td>
    </tr>
    <tr>
            <td>sef</td>
            <td>B</td>
            <td>0.22</td>
            <td>JPY</td>
            <td>01 Jan 2016</td>
            <td>02 Jan 2016</td>
            <td>50</td>
            <td>250</td>
    </tr>
     <tr>
            <td>sef</td>
            <td>S</td>
            <td>0.22</td>
            <td>JPY</td>
            <td>11 Jun 2016</td>
            <td>12 Jun 2016</td>
            <td>5000</td>
            <td>250</td>
    </tr>
</table>        
