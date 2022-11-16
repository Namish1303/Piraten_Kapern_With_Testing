Feature: Miscellaneous Fortune Cards and Full Chest bonus

  @Sorceress
  Scenario Outline: row 77,78,79
    Given player rolls <rolls>
    When Card is "Sorceress" with value 0 and bonus 0
    Then player re-rolls dices <number> and gets <reroll>
    And player re-rolls dices <number2> and gets <reroll2>
    And scores <score>
    Examples:
      | rolls                                                   |score  | number    | reroll                                                  | number2 | reroll2                                                 |
      | "Diamond,Diamond,Sword,Monkey,Coin,Parrot,Parrot,Parrot"| 500   | "6,7,8"   | "Diamond,Diamond,Sword,Monkey,Coin,Skull,Monkey,Monkey" | "6"     | "Diamond,Diamond,Sword,Monkey,Coin,Monkey,Monkey,Monkey"|
      | "Skull,Skull,Skull,Parrot,Parrot,Parrot,Sword,Sword"    | 1000  | "1"       | "Parrot,Skull,Skull,Parrot,Parrot,Parrot,Sword,Sword"   |  "7,8"  | "Parrot,Skull,Skull,Parrot,Parrot,Parrot,Parrot,Parrot" |
      | "Skull,Parrot,Parrot,Parrot,Parrot,Monkey,Monkey,Monkey"| 2000  | "6,7,8"   | "Skull,Parrot,Parrot,Parrot,Parrot,Skull,Parrot,Parrot" |  "6"    | "Skull,Parrot,Parrot,Parrot,Parrot,Parrot,Parrot,Parrot"|



  @MonkeyBusiness1
  Scenario: row 82
    Given player rolls "Monkey,Monkey,Monkey,Parrot,Parrot,Parrot,Skull,Coin"
    When Card is "Monkey Business" with value 0 and bonus 0
    Then Player scores 1100


  @MonkeyBusiness2
  Scenario: row 83
    Given player rolls "Monkey,Monkey,Sword,Sword,Parrot,Parrot,Coin,Coin"
    When Card is "Monkey Business" with value 0 and bonus 0
    Then player re-rolls dices "3,4" and gets "Monkey,Monkey,Monkey,Parrot,Parrot,Parrot,Coin,Coin"
    And scores 1700


  @MonkeyBusiness3
  Scenario: row 84
    Given player rolls "Skull,Skull,Skull,Parrot,Parrot,Monkey,Monkey,Monkey"
    When Card is "Monkey Business" with value 0 and bonus 0
    Then Player dies and scores 0



  @FullChest1
  Scenario Outline: row 97,98,99,103
    Given player rolls <rolls>
    When Card is <card> with value <value> and bonus <bonus>
    Then Player scores <score>
    Examples:
      | rolls                                                    | card             | value| bonus |score  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Diamond,Parrot"  | "Coin"           |  0   |   0   |  400  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Coin,Coin"       | "Captain"        |  0   |   0   |  1800 |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Sword,Diamond"   | "Coin"           |  0   |   0   |  1000 |
      | "Monkey,Monkey,Parrot,Coin,Coin,Diamond,Diamond,Diamond" | "Monkey Business"|  0   |   0   |  1200 |
    
    
  @FullChest2
  Scenario: row 100
    Given player rolls "Monkey,Monkey,Monkey,Monkey,Sword,Parrot,Parrot,Coin"
    When Card is "Sea Battle" with value 2 and bonus 300
    Then player re-rolls dices "6,7" and gets "Monkey,Monkey,Monkey,Monkey,Sword,Coin,Sword,Coin"
    And scores 1200 with SeaBattle Points



  @SeaBattle11
  Scenario Outline: row 117,120,123
    Given player rolls <rolls>
    When Card is "Sea Battle" with value <value> and bonus <bonus>
    Then scores <score> with SeaBattle Points
    Examples:
      | rolls                                                    |value| bonus |score  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Coin,Parrot,Parrot"    |  2  |   300 |  500  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Sword,Skull"     |  3  |   500 |  800  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Sword,Skull"     |  4  |  1000 |  1300 |


  @SeaBattle10
  Scenario Outline: row 114,116
    Given player rolls <rolls>
    When Card is "Sea Battle" with value <value> and bonus <bonus>
    Then Player dies and gets a deduction of <score>
    Examples:
      | rolls                                                    |value| bonus |score  |
      | "Monkey,Monkey,Monkey,Monkey,Sword,Skull,Skull,Skull"    |  2  |   300 |  -300 |
      | "Monkey,Monkey,Sword,Sword,Sword,Skull,Skull,Skull"      |  4  |   1000|  -1000|


  @SeaBattle20
  Scenario Outline: row 115,121
    Given player rolls <rolls>
    When Card is "Sea Battle" with value <value> and bonus <bonus>
    Then player re-rolls dices <number> and gets <reroll>
    And Player dies and gets a deduction of <score>
    Examples:
      | rolls                                                    |value| bonus | score | number   | reroll                                              |
      | "Sword,Sword,Skull,Skull,Parrot,Parrot,Parrot,Parrot"    |  3  |   500 |  -500 | "5,6,7,8"|"Sword,Sword,Skull,Skull,Skull,Skull,Skull,Skull"    |
      | "Monkey,Monkey,Monkey,Monkey,Sword,Sword,Skull,Skull"    |  3  |   500 |  -500 | "1,2,3,4"|"Skull,Skull,Sword,Sword,Sword,Sword,Skull,Skull"    |


  @SeaBattle21
  Scenario: row 119
    Given player rolls "Monkey,Monkey,Monkey,Monkey,Sword,Skull,Parrot,Parrot"
    When Card is "Sea Battle" with value 2 and bonus 300
    Then player re-rolls dices "7,8" and gets "Monkey,Monkey,Monkey,Monkey,Sword,Skull,Sword,Skull"
    And scores 500 with SeaBattle Points


  @SeaBattle31
  Scenario: row 126
    Given player rolls "Monkey,Monkey,Monkey,Diamond,Sword,Skull,Parrot,Parrot"
    When Card is "Sea Battle" with value 4 and bonus 1000
    Then player re-rolls dices "7,8" and gets "Monkey,Monkey,Monkey,Diamond,Sword,Skull,Sword,Sword"
    And player re-rolls dices "1,2,3" and gets "Sword,Parrot,Parrot,Diamond,Sword,Skull,Sword,Sword"
    And scores 1300 with SeaBattle Points
    
    
    
  @Skull10
  Scenario Outline: row 106,107
    Given player rolls <rolls>
    When Card is "Skulls" with value <value> and bonus <bonus>
    Then dies and scores 0
    Examples:
      | rolls                                                    |value| bonus |
      | "Skull,Sword,Sword,Sword,Sword,Sword,Sword,Sword"        |  2  |   0   |
      | "Skull,Skull,Sword,Sword,Sword,Sword,Sword,Sword"        |  1  |   0   |


  @Skull21
  Scenario Outline: row 110,111
    Given player rolls <rolls>
    When Card is <card> with value <value> and bonus <bonus>
    Then player goes to Isle of Dead
    And player re-rolls dices <number> and gets <reroll>
    And incurs a deduction of <score> for other players
    Examples:
      | rolls                                                |value| bonus | score | number     | reroll                                         | card     |
      | "Skull,Skull,Skull,Skull,Skull,Monkey,Monkey,Monkey" |  0  |   0   |  1400 | "6,7,8"    |"Skull,Skull,Skull,Skull,Skull,Skull,Skull,Coin"|"Captain" |
      | "Skull,Skull,Skull,Sword,Sword,Sword,Sword,Sword"    |  2  |   0   |  500  | "4,5,6,7,8"|"Skull,Skull,Skull,Coin,Coin,Coin,Coin,Coin"    |"Skulls"  |


