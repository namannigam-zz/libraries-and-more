package hashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMap {


    public static void main(String[] args) {
        BonusType searchedBonusType = new BonusType();
        List<LandResearch> landResearches = new ArrayList<>();
        List<Bonus> bonuses = landResearches.stream()
                .flatMap(landResearch -> landResearch.getResearch().getBonuses().stream())
                .filter(bonus -> bonus.getBonusType().equals(searchedBonusType))
                .collect(Collectors.toList());
    }

    private class LandResearch {
        Research research;

        public Research getResearch() {
            return research;
        }
    }

    private class Research {
        List<Bonus> bonuses;

        public List<Bonus> getBonuses() {
            return bonuses;
        }
    }

    private class Bonus {
        BonusType bonusType;
        double bonusValue;

        public BonusType getBonusType() {
            return bonusType;
        }

        public double getBonusValue() {
            return bonusValue;
        }
    }

    private static class BonusType {
    }
}