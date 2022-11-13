Feature: Miscellaneous Fortune Cards and Full Chest bonus

  @Sorceress
  Scenario Outline: Player has sorceress card and re-roll twice
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
  Scenario: Player has monkey-business and rolls once and scores
    Given player rolls "Monkey,Monkey,Monkey,Parrot,Parrot,Parrot,Skull,Coin"
    When Card is "Monkey Business" with value 0 and bonus 0
    Then Player scores 1100


  @MonkeyBusiness2
  Scenario: Player has monkey-business and re-rolls once and scores
    Given player rolls "Monkey,Monkey,Sword,Sword,Parrot,Parrot,Coin,Coin"
    When Card is "Monkey Business" with value 0 and bonus 0
    Then player re-rolls dices "3,4" and gets "Monkey,Monkey,Monkey,Parrot,Parrot,Parrot,Coin,Coin"
    And scores 1700


  @MonkeyBusiness3
  Scenario: Player has monkey-business and rolls once and dies
    Given player rolls "Skull,Skull,Skull,Parrot,Parrot,Monkey,Monkey,Monkey"
    When Card is "Monkey Business" with value 0 and bonus 0
    Then Player dies and scores 0



  @FullChest1
  Scenario Outline: Player rolls once and scores
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
  Scenario: Player has sea battle card and re-rolls once and scores
    Given player rolls "Monkey,Monkey,Monkey,Monkey,Sword,Parrot,Parrot,Coin"
    When Card is "Sea Battle" with value 2 and bonus 300
    Then player re-rolls dices "6,7" and gets "Monkey,Monkey,Monkey,Monkey,Sword,Coin,Sword,Coin"
    And scores 1200 with SeaBattle Points



  @SeaBattle11
  Scenario Outline: Player has Sea Battle card and rolls once and scores
    Given player rolls <rolls>
    When Card is "Sea Battle" with value <value> and bonus <bonus>
    Then scores <score> with SeaBattle Points
    Examples:
      | rolls                                                    |value| bonus |score  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Coin,Parrot,Parrot"    |  2  |   300 |  500  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Sword,Skull"     |  3  |   500 |  800  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Sword,Skull"     |  4  |  1000 |  1300 |


  @SeaBattle10
  Scenario Outline: Player has Sea Battle card and rolls once and dies
    Given player rolls <rolls>
    When Card is "Sea Battle" with value <value> and bonus <bonus>
    Then Player dies and gets a deduction of <score>
    Examples:
      | rolls                                                    |value| bonus |score  |
      | "Monkey,Monkey,Monkey,Monkey,Sword,Skull,Skull,Skull"    |  2  |   300 |  -300 |
      | "Monkey,Monkey,Sword,Sword,Sword,Skull,Skull,Skull"      |  4  |   1000|  -1000|


  @SeaBattle20
  Scenario Outline: Player has Sea Battle card and re-rolls once and dies
    Given player rolls <rolls>
    When Card is "Sea Battle" with value <value> and bonus <bonus>
    Then player re-rolls dices <number> and gets <reroll>
    And Player dies and gets a deduction of <score>
    Examples:
      | rolls                                                    |value| bonus | score | number   | reroll                                              |
      | "Sword,Sword,Skull,Skull,Parrot,Parrot,Parrot,Parrot"    |  3  |   500 |  -500 | "5,6,7,8"|"Sword,Sword,Skull,Skull,Skull,Skull,Skull,Skull"    |
      | "Monkey,Monkey,Monkey,Monkey,Sword,Sword,Skull,Skull"    |  3  |   500 |  -500 | "1,2,3,4"|"Skull,Skull,Sword,Sword,Sword,Sword,Skull,Skull"    |


  @SeaBattle21
  Scenario: Player has Sea Battle card and re-rolls once and scores
    Given player rolls "Monkey,Monkey,Monkey,Monkey,Sword,Skull,Parrot,Parrot"
    When Card is "Sea Battle" with value 2 and bonus 300
    Then player re-rolls dices "7,8" and gets "Monkey,Monkey,Monkey,Monkey,Sword,Skull,Sword,Skull"
    And scores 500 with SeaBattle Points


  @SeaBattle31
  Scenario: Player has Sea Battle card and re-rolls twice and scores
    Given player rolls "Monkey,Monkey,Monkey,Diamond,Sword,Skull,Parrot,Parrot"
    When Card is "Sea Battle" with value 4 and bonus 1000
    Then player re-rolls dices "7,8" and gets "Monkey,Monkey,Monkey,Diamond,Sword,Skull,Sword,Sword"
    And player re-rolls dices "1,2,3" and gets "Sword,Parrot,Parrot,Diamond,Sword,Skull,Sword,Sword"
    And scores 1300 with SeaBattle Points
    
    
    
  @Skull10
  Scenario Outline: Player rolls once and dies
    Given player rolls <rolls>
    When Card is "Skull" with value <value> and bonus <bonus>
    Then dies and scores 0
    Examples:
      | rolls                                                    |value| bonus |
      | "Skull,Sword,Sword,Sword,Sword,Sword,Sword,Sword"        |  2  |   0   |
      | "Skull,Skull,Sword,Sword,Sword,Sword,Sword,Sword"        |  1  |   0   |