Feature: Testing single player scoring

  @SRPL
  Scenario Outline: Single roll outcomes (no death)
    Given player rolls <rolls>
    When Card is <card> with value <value> and bonus <bonus>
    Then Player scores <score>
    Examples:
      | rolls                                                    | card          | value| bonus |score  |
      | "Monkey,Monkey,Parrot,Parrot,Diamond,Diamond,Coin,Coin"  | "Captain"     |  0   |   0   |  800  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Skull,Skull"     | "Coin"        |  0   |   0   |  300  |
      | "Diamond,Diamond,Diamond,Skull,Skull,Parrot,Sword,Monkey"| "Coin"        |  0   |   0   |  500  |
      | "Coin,Coin,Coin,Coin,Skull,Skull,Sword,Sword"            | "Diamond"     |  0   |   0   |  700  |
      | "Sword,Sword,Sword,Parrot,Parrot,Parrot,Parrot,Skull"    | "Coin"        |  0   |   0   |  400  |
      | "Monkey,Monkey,Monkey,Monkey,Monkey,Monkey,Skull,Skull"  | "Coin"        |  0   |   0   |  1100 |
      | "Parrot,Parrot,Parrot,Parrot,Parrot,Parrot,Parrot,Skull" | "Coin"        |  0   |   0   |  2100 |
      | "Coin,Coin,Coin,Coin,Coin,Coin,Coin,Coin"                | "Coin"        |  0   |   0   |  5400 |
      | "Coin,Coin,Coin,Coin,Coin,Coin,Coin,Coin"                | "Diamond"     |  0   |   0   |  5400 |
      | "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Sword"        | "Captain"     |  0   |   0   |  9000 |
      | "Monkey,Monkey,Monkey,Monkey,Coin,Coin,Skull,Skull"      | "Coin"        |  0   |   0   |  600  |


    @SRPD
    Scenario Outline: Player rolls and dies
      Given player rolls <rolls>
      When Card is <card> with value <value> and bonus <bonus>
      Then Player dies and scores <score>
      Examples:
        | rolls                                                    | card          | value| bonus |score  |
        | "Skull,Skull,Skull,Sword,Sword,Sword,Sword,Sword"        | "Coin"        |  0   |   0   |  0    |


    @DRPD
    Scenario Outline: Player re-rolls once and dies
      Given player rolls <rolls>
      When Card is <card> with value <value> and bonus <bonus>
      Then player re-rolls dices <number> and gets <reroll>
      And dies and scores <score>
      Examples:
        | rolls                                                    | card          | value| bonus |score  | number    | reroll                                                   |
        | "Skull,Parrot,Parrot,Parrot,Parrot,Sword,Sword,Sword"    | "Coin"        |  0   |   0   | 0     | "6,7,8"   | "Skull,Parrot,Parrot,Parrot,Parrot,Sword,Skull,Skull"    |
        | "Skull,Parrot,Parrot,Parrot,Parrot,Skull,Sword,Sword"    | "Coin"        |  0   |   0   | 0     | "7,8"     | "Skull,Parrot,Parrot,Parrot,Parrot,Skull,Skull,Sword"    |

  @DRPL
  Scenario Outline: Player re-rolls once and scores
    Given player rolls <rolls>
    When Card is <card> with value <value> and bonus <bonus>
    Then player re-rolls dices <number> and gets <reroll>
    And scores <score>
    Examples:
      | rolls                                                    | card          | value| bonus |score  | number    | reroll                                                   |
      | "Monkey,Monkey,Skull,Skull,Sword,Sword,Parrot,Parrot"    | "Coin"        |  0   |   0   | 300   | "7,8"     | "Monkey,Monkey,Skull,Skull,Sword,Sword,Sword,Monkey"     |
      | "Skull,Parrot,Parrot,Coin,Coin,Sword,Sword,Sword"        | "Coin"        |  0   |   0   | 800   | "2,3"     | "Skull,Coin,Sword,Coin,Coin,Sword,Sword,Sword"           |
      | "Skull,Parrot,Parrot,Coin,Coin,Sword,Sword,Sword"        | "Captain"     |  0   |   0   | 1200  | "2,3"     | "Skull,Coin,Sword,Coin,Coin,Sword,Sword,Sword"           |
      | "Monkey,Monkey,Monkey,Monkey,Monkey,Monkey,Sword,Sword"  | "Coin"        |  0   |   0   | 4600  | "7,8"     | "Monkey,Monkey,Monkey,Monkey,Monkey,Monkey,Monkey,Monkey"|
      | "Monkey,Monkey,Skull,Skull,Sword,Sword,Parrot,Parrot"    | "Diamond"     |  0   |   0   | 400   | "7,8"     | "Monkey,Monkey,Skull,Skull,Sword,Sword,Diamond,Diamond"  |
      | "Monkey,Monkey,Skull,Skull,Sword,Sword,Diamond,Parrot"   | "Coin"        |  0   |   0   | 500   | "1,2"     | "Diamond,Diamond,Skull,Skull,Sword,Sword,Diamond,Parrot" |
      | "Skull,Parrot,Monkey,Coin,Coin,Sword,Sword,Sword"        | "Coin"        |  0   |   0   | 600   | "6,7,8"   | "Skull,Parrot,Monkey,Coin,Coin,Coin,Monkey,Parrot"       |
      | "Skull,Parrot,Monkey,Coin,Coin,Sword,Sword,Sword"        | "Diamond"     |  0   |   0   | 500   | "6,7,8"   | "Skull,Parrot,Monkey,Coin,Coin,Coin,Monkey,Parrot"       |


    @TRPD
    Scenario: Player re-rolls twice and dies
      Given player rolls "Skull,Parrot,Parrot,Parrot,Parrot,Sword,Sword,Sword"
      When Card is "Coin" with value 0 and bonus 0
      Then player re-rolls dices "6,7,8" and gets "Skull,Parrot,Parrot,Parrot,Parrot,Skull,Monkey,Monkey"
      And player re-rolls dices "7,8" and gets "Skull,Parrot,Parrot,Parrot,Parrot,Skull,Skull,Monkey"
      And dies and scores 0
