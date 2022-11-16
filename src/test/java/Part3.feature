Feature: Multiplayer testing

  Scenario: row130
    Given Game has started
    When player 1 has Card "Captain" with value 0 and bonus 0
    And player 1 rolls "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Skull" and stops
    Then player 2 has Card "Skulls" with value 1 and bonus 0
    And player 2 rolls "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Skull" and stops
    Then player 3 has Card "Coin" with value 0 and bonus 0
    And player 3 rolls "Skull,Skull,Monkey,Monkey,Monkey,Monkey,Monkey,Skull" and stops
    Then game is over and player 1 wins


  Scenario: row134
    Given Game has started
    When player 1 has Card "Captain" with value 0 and bonus 0
    And player 1 rolls "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Skull" and stops
    Then player 2 has Card "Coin" with value 0 and bonus 0
    And player 2 rolls "Skull,Skull,Monkey,Monkey,Monkey,Monkey,Monkey,Skull" and stops
    Then player 3 has Card "Captain" with value 0 and bonus 0
    And player 3 rolls "Skull,Skull,Skull,Skull,Skull,Skull,Parrot,Parrot" and stops
    Then player 1 has Card "Coin" with value 0 and bonus 0
    And player 1 rolls "Monkey,Parrot,Monkey,Parrot,Monkey,Parrot,Monkey,Parrot" and stops
    Then player 2 has Card "Captain" with value 0 and bonus 0
    And player 2 rolls "Skull,Skull,Monkey,Monkey,Monkey,Monkey,Monkey,Skull" and stops
    Then player 3 has Card "Skulls" with value 1 and bonus 0
    And player 3 rolls "Skull,Skull,Monkey,Monkey,Monkey,Monkey,Monkey,Monkey" and stops
    Then game is over and player 1 wins

  Scenario: row 142
    Given Game has started
    When player 1 has Card "Captain" with value 0 and bonus 0
    And player 1 rolls "Skull,Skull,Monkey,Monkey,Monkey,Monkey,Monkey,Skull" and stops
    Then player 2 has Card "Captain" with value 0 and bonus 0
    And player 2 rolls "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Skull" and stops
    Then player 3 has Card "Skulls" with value 2 and bonus 0
    And player 3 rolls "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Skull" and stops
    Then player 1 has Card "Captain" with value 0 and bonus 0
    And player 1 rolls "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Sword" and stops
    Then game is over and player 1 wins

  Scenario: 147
    Given Game has started
    When player 1 has Card "Coin" with value 0 and bonus 0
    And player 1 rolls "Sword,Sword,Sword,Sword,Sword,Sword,Skull,Skull" and stops
    Then player 2 has Card "Sorceress" with value 0 and bonus 0
    And player 2 rolls "Skull,Skull,Skull,Skull,Skull,Skull,Skull,Coin"
    Then player 2 rerolls "1" and gets "Parrot,Skull,Skull,Skull,Skull,Skull,Skull,Coin"
    And player 2 rerolls "1,8" and gets "Skull,Skull,Skull,Skull,Skull,Skull,Skull,Skull" and stops
    Then game is over and player 1 scores 300 , player 2 scores 0 and player 3 scores 0


