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
</table>        
<ul><li>A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where the work week starts Sunday and ends Thursday. No other holidays to be taken into account.</li>
    <li>A trade can only be settled on a working day.</li>
    <li>If an instructed settlement date falls on a weekend, then the settlement date should be changed to the next working day.</li>
    <li>USD amount of a trade = Price per unit * Units * Agreed Fx</li>
    </ul>
    <p>Requirements</p>
    <ul>
        <li>Create a report that shows</li>
        <li>Amount in USD settled incoming everyday</li><li>Amount in USD settled outgoing everyday</li>
        <li>Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest amount for a buy instruction, then foo is rank 1 for outgoing</li>
    </ul>
