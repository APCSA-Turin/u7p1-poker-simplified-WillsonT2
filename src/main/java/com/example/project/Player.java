package com.example.project;
import java.util.ArrayList;
import java.util.List;


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
        hand.add(c);
        allCards.add(c);
    }

    public boolean checkFlush(){
        ArrayList<Integer> suitFrequency = findSuitFrequency();
        if (suitFrequency.contains(5)){
            return true;
        }
        return false;
    }

    public boolean checkStraight(){
        sortAllCards();
        int prevCardVaulue = Utility.getRankValue(allCards.get(0).getRank());
        for (int i = 1; i < allCards.size(); i++){
            int cardValue = Utility.getRankValue(allCards.get(i).getRank());
            if (!(cardValue == prevCardVaulue + 1)){
                return false;
            }
            prevCardVaulue = cardValue;
        }
        return true;
    }

    public boolean checkTwoPair(){
        boolean firstPair = false;
        boolean secondPair = false;
        ArrayList<Integer> rankingFrequency = findRankingFrequency();
        for (int i = 0; i < rankingFrequency.size(); i++){
            if (!(firstPair) && rankingFrequency.get(i) == 2){
                firstPair = true;
            }else if (firstPair && !(secondPair) && rankingFrequency.get(i) == 2){
                secondPair = true;
            }
        }
        return firstPair && secondPair;
    }

    public boolean checkHighCard(){
        int highHandCard = Utility.getRankValue(hand.get(0).getRank());
        int highAllCard = Utility.getRankValue(hand.get(0).getRank());
        for (int i = 0; i < hand.size(); i++){
            if (Utility.getRankValue(hand.get(i).getRank()) > highHandCard){
                highHandCard = Utility.getRankValue(hand.get(i).getRank());;
            }
        }
        for (int i = 0; i < allCards.size(); i++){
            if (Utility.getRankValue(allCards.get(i).getRank()) > highAllCard){
                highAllCard = Utility.getRankValue(allCards.get(i).getRank());;
            }
        }
        return highHandCard > highAllCard;
    }

    public String playHand(ArrayList<Card> communityCards){
        for (int i = 0; i < communityCards.size(); i++){
            allCards.add(communityCards.get(i));
        }
        ArrayList<Integer> rankingFrequency = findRankingFrequency();
        ArrayList<Integer> suitFrequency = findSuitFrequency();
        System.out.println(rankingFrequency);
        System.out.println(suitFrequency);
        if (checkFlush() && checkStraight() && rankingFrequency.get(8) == 1 && rankingFrequency.get(12) == 1){
            return "Royal Flush";
        }else if (checkFlush() && checkStraight()){
            return "Straight Flush";
        }else if (rankingFrequency.contains(4)){
            return "Four of a Kind";
        }else if (rankingFrequency.contains(3) && rankingFrequency.contains(2)){
            return "Full House";
        }else if (rankingFrequency.contains(3)){
            return "Three of a Kind";
        }else if (checkTwoPair()){
            return "Two Pair";
        }else if (rankingFrequency.contains(2)){
            return "A Pair";
        }else if (checkHighCard()){
            return "High Card";
        }
        return "Nothing";
    }

    public void sortAllCards(){
        for (int i = 0; i < hand.size(); i++){
            int idx = i;
            for (int j = i + 1; 0 < j && j < hand.size(); j--){
                if (Utility.getRankValue(hand.get(j).getRank()) < Utility.getRankValue(hand.get(idx).getRank())){
                    Card temp = hand.get(j);
                    hand.set(j, hand.get(idx));
                    hand.set(idx, temp);
                    idx--;
                }else {
                    break;
                }
            }
        }
        for (int i = 0; i < allCards.size(); i++){
            int idx = i;
            for (int j = i + 1; 0 < j && j < allCards.size(); j--){
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(idx).getRank())){
                    Card temp = allCards.get(j);
                    allCards.set(j, allCards.get(idx));
                    allCards.set(idx, temp);
                    idx--;
                }else {
                    break;
                }
            }
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankingFrequencey = new ArrayList<>(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        for (int i = 0; i < rankingFrequencey.size(); i++){
            for (int j = 0; j < allCards.size(); j++){
                if (Utility.getRankValue(allCards.get(j).getRank()) - 2 == i){
                    rankingFrequencey.set(i, rankingFrequencey.get(i) + 1);
                }
            }
        }
        return rankingFrequencey; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<>(List.of(0, 0, 0, 0));
        for (int i = 0; i < allCards.size(); i++){
            suitFrequency.set(Utility.getSuitValue(allCards.get(i).getSuit()), suitFrequency.get(Utility.getSuitValue(allCards.get(i).getSuit())) + 1);
        }
        return suitFrequency; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
