import java.util.Random;

public class Main {
    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {250, 270, 280, 380, 450,    /*270*/};
    public static int[] heroesDamage = {25, 20, 15, 0, 5,     /*15*/};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Healing", "Golem", /*"Berserk"*/};
    public static int roundNumber = 0;

    public static Random random = new Random();


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {

        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        /*for (int i = 0; i < heroesHealth.length; i++) {
            int[] unknownMassive = {100};
            if (heroesHealth[i] < unknownMassive[0]);
            Random random = new Random();
            int healingPower = random.nextInt(230)+50;
            heroesHealth[i] = heroesHealth[i] + healingPower;
            break;
        }*/
        printStatistics();

    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: " + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + "; damage: "
                    + heroesDamage[i]);
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
       /* String healing[] = {"Healing"};
        if (heroesAttackType != healing){*/
        bossDefenceType = heroesAttackType[randomIndex];
        /*}*/


    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {

        for (int i = 0; i < heroesHealth.length; i++) {
            int damageToGolem = bossDamage / 5;
         /*   Random random= new Random();
            int lucky= random.nextInt(1);
            if (lucky == 1) {
                //bossDamage = bossDamage - bossDamage;
                heroesHealth[5] = heroesHealth[5] + 50;
                continue;
            }else if (lucky == 0){
            }*/
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                }
                if (heroesHealth[4] < 0) {
                    heroesHealth[4] = 0;
                    damageToGolem = 0;
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                } else {
                    bossDamage = bossDamage - damageToGolem;
                    heroesHealth[4] = heroesHealth[4] - damageToGolem;
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                    bossDamage = bossDamage + damageToGolem;
                }
            }
        }
        healingPower();
    }

    /* public static void luckyLucky () {
     }*/
    public static void healingPower() {
        for (int i = 0; i < heroesHealth.length; i++) {
            Random random = new Random();
            int healingPower = random.nextInt(100) + 50;
            if (heroesHealth[3] == 0) {
                break;
            } else if (heroesAttackType[i] == "Healing") {
                break;
            } else if (heroesHealth[i] > 100) {
                continue;
            } else if (heroesHealth[i] < 100) {
                heroesHealth[i] += healingPower;
                break;
            }
        }
    }

    public static void heroesHit() {

        for (int i = 0; i < heroesDamage.length; i++) {
           /* int berserk = random.nextInt(1);
            int berserr = random.nextInt(50) + 1;*/
           /* if (berserk == 1){
                int bossDamageForBers;
                bossDamageForBers = bossDamage - berserk;
                heroesDamage[5] = heroesDamage[5] + berserk;*/

            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(6) + 2; // 2,3,4,5,6,7
                    hit = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }

            }
        }
    }
}