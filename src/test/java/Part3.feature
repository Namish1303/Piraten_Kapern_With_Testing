Feature: Multiplayer testing

  Scenario: Each player has single roll
    Given Game has started
    When player 1 has Card "Captain" with value 0 and bonus 0
    And player 1 rolls "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Skull" and stops
    Then player 2 has Card "Skulls" with value 1 and bonus 0
    And player 2 rolls "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Skull" and stops
    Then player 3 has Card "Coin" with value 0 and bonus 0
    And player 3 rolls "Skull,Skull,Monkey,Monkey,Monkey,Monkey,Monkey,Skull" and stops
    Then game is over and player 1 wins


