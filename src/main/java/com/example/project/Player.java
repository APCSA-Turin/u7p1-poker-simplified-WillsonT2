package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        
    }

    public String playHand(ArrayList<Card> communityCards){      
        return "Nothing";
    }

    public void sortAllCards(){} 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankingFrequencey = new ArrayList<>(13);
        for (int i = 0; i < rankingFrequencey.size(); i++){
            for (int j = 0; j < allCards.size(); j++){
                if (Utility.getRankValue(allCards.get(j).getRank()) - 2 == i){
                    rankingFrequencey.add(i, rankingFrequencey.remove(i) + 1);
                }
            }
        }
        return rankingFrequencey; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<>(4);
        for (int i = 0; i < suitFrequency.size(); i++){
            for (int j = 0; j < allCards.size(); j++){
                if (allCards.get(j).getSuit().equals(suitFrequency)){
                    
                }
            }
        }
        return new ArrayList<>(); 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
