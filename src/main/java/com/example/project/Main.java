package com.example.project;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        player.addCard(new Card("9", "♠"));
        player.addCard(new Card("9", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("9", "♣"));
        communityCards.add(new Card("9", "♥"));
        communityCards.add(new Card("A", "♦"));
        player.playHand(communityCards);
        System.out.println(player.playHand(communityCards));
    }
}
