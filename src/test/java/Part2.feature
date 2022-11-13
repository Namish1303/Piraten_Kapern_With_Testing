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
      | rolls                                                    | card       | value| bonus |score  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Diamond,Parrot"  | "Coin"     |  0   |   0   |  400  |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Coin,Coin"       | "Captain"  |  0   |   0   |  1800 |
      | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Sword,Diamond"   | "Coin"     |  0   |   0   |  1000 |
