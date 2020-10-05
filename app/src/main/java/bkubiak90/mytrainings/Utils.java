package bkubiak90.mytrainings;

import java.util.ArrayList;

public class Utils {

    private static ArrayList<Training> trainings;
    private static ArrayList<Plan> plans;

    public static void initTrainings () {

        if (null == trainings) {
            trainings = new ArrayList<>();


            Training pushUp = new Training(1, "Pompki", "Push-Ups",
                    "Połóż dłonie płasko na podłodze na szerokość ramion. Wysuń nogi do tyłu opierając o ziemię tylko palcami stóp. Na wyprostowanych rękach utrzymaj ciało prosto, szyja i głowa powinny być jego przedłużeniem, a wzrok skierowany prostopadle. Opuszczaj klatke piersiową do podłogi uginając ręce, do momentu aż znajdzie się tuż nad nią. Wstrzymaj na sekundę i powróć do pozycji startywej prostując ręce, odczekaj sekundę. Powtarzaj.",
                    R.mipmap.pushups, "powtórzeń");
            trainings.add(pushUp);

            Training jumpRope = new Training(2, "Skakanka", "Basic Jump",
                    "Złap uchwyty skakanki w obie dłonie, a linkę ułóż za sobą. Utrzymuj proste plecy, napięte mieśnie brzucha, a ręce lekko ugięte w łokciach. Obracaj nadgarstkami aby przeżucić linkę ponad głową, a kiedy opadnie w dół przeskocz nad nią obiema nogami. Nogi powinny być lekko zgięte w kolanach, a skoki wykonywane miekko na przedniej części stopy. Pięty nie powinny dotykać podłogi. Kontynuuj obracanie nadgarstków i przeskakuj nad skakanką.",
                    R.mipmap.skipping, "min");
            trainings.add(jumpRope);

            Training pullup = new Training(3, "Podciąganie", "Pull-Ups",
                    "Chwyć drążek oburącz, nachwytem tak aby ręce były trochę szerzej niż szerokość barków. Wykonuj podciągniecia, do momentu aż koniec brody znajdzie się ponad drążkiem. Szyja i głowa powinny być przedłużeniem Twojego ciała, nie należy wyciągać brody ku górze. Powtórzenie wykonywać równomiernie, bez kołysania ciałem.",
                    R.mipmap.pullups, "podciągnięć");
            trainings.add(pullup);
        }

    }

    public static ArrayList<Training> getTrainings() {
        return trainings;
    }

    public static boolean addPlan(Plan plan) {
        if (null == plans) {
            plans = new ArrayList<>();
        }

        return plans.add(plan);
    }

    public static ArrayList<Plan> getPlans() {
        return plans;
    }

    public static boolean removePlan(Plan plan) {
        return  plans.remove(plan);
    }
}
