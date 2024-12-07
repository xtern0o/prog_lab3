package org.example;

import org.example.abstract_classes.Soul;
import org.example.classes.*;
import org.example.enums.*;
import org.example.interfaces.Action;
import org.example.records.Speach;

public class Main {
    public static void main(String[] args) {
        Location startLocation = new Location("Стартовая локация");
        Room cellar = new Room("Погреб", 10, 3);

        Worker baronApelsin = new Worker("Апельсин", 4);
        Worker ragmanFasol = new Worker("Фасоль", 4);
        Worker dukeMandarin = new Worker("Мандарин", SoulState.NORMAL, null, 4, WorkerRole.DUKE, false, WorkStage.DO_NOTHING);
        Person someone = new Person("Кто-то", 4);
        Person you = new Person("Вы (читатель)", 0);

        Item treasure = new Item("Сокровище", cellar, ItemState.HIDDEN);
        AlcoholicBottle wineBottle = new AlcoholicBottle("Бутылка вина", cellar, true, (byte) 127);

        baronApelsin.setRole(WorkerRole.BARON);
        ragmanFasol.setRole(WorkerRole.RAGMAN);

        Action mandarinsFakeStory = new Action() {
            @Override
            public void run() {
                dukeMandarin.printCurrentWorkStage();
                baronApelsin.printState();
                for (Item i : baronApelsin.getWishItems()) {
                    System.out.printf("%s хочет %s", baronApelsin, i);
                }
            }
        };

        Action robTheTreasure = new Action() {
            @Override
            public void run() {
                Worker dukeMandarinClone = dukeMandarin.clone();

                dukeMandarinClone.setLocation(cellar);

                if (dukeMandarinClone.isAloneAtLocation()) System.out.printf("%s в %s - один\n", dukeMandarinClone, dukeMandarinClone.getLocation());
                else System.out.printf("%s в %s - не один\n", dukeMandarinClone, dukeMandarinClone.getLocation());

                dukeMandarinClone.takeItem(treasure);

                dukeMandarinClone.setLocation(null);
            }
        };

        Action dukeAndBaronInTheCellar = new Action() {
            @Override
            public void run() {
                baronApelsin.addWishItem(wineBottle);
                baronApelsin.setState(SoulState.THIRST);
                baronApelsin.printState();

                dukeMandarin.setCurrentWorkStage(WorkStage.ESCORTING_THE_BARON);

                baronApelsin.setLocation(cellar);
                dukeMandarin.setLocation(cellar);

                if (Math.random() < 0.5) {
                    someone.setLocation(cellar);
                }

                System.out.printf("В %s сейчас:\n", cellar);
                for (Soul s : cellar.getSouls()) {
                    System.out.println(s);
                }

                if (someone.getLocation() != null) {

                    if (someone.lookAt(dukeMandarin)) {
                        dukeMandarin.explain(mandarinsFakeStory);
                    }
                    baronApelsin.takeItem(wineBottle);
                    baronApelsin.drink(wineBottle);

                    if (!someone.lookAt(dukeMandarin) && !baronApelsin.lookAt(dukeMandarin)) {
                        Speach successSpeach = new Speach(dukeMandarin, "никто не смотрит! Можно попытаться украсть сокровище");
                        dukeMandarin.say(successSpeach);
                        dukeMandarin.takeItem(treasure);
                    }
                }
                else {
                    System.out.println();
                    baronApelsin.takeItem(wineBottle);
                    baronApelsin.drink(wineBottle);

                    if (!baronApelsin.lookAt(dukeMandarin)) {
                        Speach successSpeach = new Speach(dukeMandarin, "Барон Апельсин не смотрит! Можно попытаться украсть сокровище");
                        dukeMandarin.say(successSpeach);
                        dukeMandarin.takeItem(treasure);
                    }
                }

                baronApelsin.setLocation(null);
                dukeMandarin.setLocation(null);

            }
        };

        Action mainAction = new Action() {
            @Override
            public void run() {
                baronApelsin.agree();
                ragmanFasol.setVacationStatus(true, Period.ALL_EVENING);
                dukeMandarin.addWishItem(treasure);

                you.askWhyNot(robTheTreasure);

                Item treasure = new Item("Сокровище", cellar);

                System.out.println("Да потому, что, если бы их застали врасплох, он мог бы свалить всю вину на барона Апельсина.");

                dukeAndBaronInTheCellar.run();

                System.out.println("Итог:");
                if (dukeMandarin.getItems().contains(treasure)) {
                    System.out.println("Герцогу Мандарину удалось украсть сокровище!!!!!!");
                }
                else {
                    System.out.println("Герцогу Мандарину не удалось украсть сокровище :(:(:(:(:(((((");
                }
            }
        };

        mainAction.run();
    }
}