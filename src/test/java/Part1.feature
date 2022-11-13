Feature: Testing single player scoring

  @SingleRollsWithoutDeath
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


    Scenario Outline: Player rolls and dies
      Given player rolls <rolls>
      When Card is <card> with value <value> and bonus <bonus>
      Then Player dies and scores <score>
      Examples:
        | rolls                                                    | card          | value| bonus |score  |
        | "Monkey,Monkey,Parrot,Parrot,Diamond,Diamond,Coin,Coin"  | "Captain"     |  0   |   0   |  800  |