package GeneticUtilities;

import Chromosome.Chromosome;

import java.util.ArrayList;
import java.util.Random;

public class Crossover {

    public static ArrayList<Chromosome> uniformCrossover(ArrayList<Chromosome> chromosomeArrayList, double crossOverRate, Random generator) {
        ArrayList<Chromosome> newChromosomes = new ArrayList<>();
        int rate = Math.max((int)(10 - (10*crossOverRate)), 0);
        boolean[] bitMask;

        while (newChromosomes.size() != chromosomeArrayList.size()) {
            Chromosome p1 = chromosomeArrayList.get(generator.nextInt(chromosomeArrayList.size()-1));
            Chromosome p2 = chromosomeArrayList.get(generator.nextInt(chromosomeArrayList.size()-1));
            Chromosome c1 = new Chromosome(p1.size);
            Chromosome c2 = new Chromosome(p2.size);

            // It includes 0 and excludes the bound thus the +1
            if (generator.nextInt(10)+1 > rate) {
                bitMask = new boolean[p1.size];
                for (int i = 0; i < bitMask.length; i++) {
                    if (generator.nextInt(10)+1 > 5) {
                        bitMask[i] = true;
                    } else {
                        bitMask[i] = false;
                    }
                }
                for (int i = 0; i < p1.size; i++) {
                    if (bitMask[i]) {
                        c1.add(i, p1.get(i));
                        c2.add(i, p2.get(i));
                    }
                }
                for (int i = 0; i < p1.size; i++) {
                    int pos = 0;
                    if (!bitMask[i]) {
                        while (c1.exists(p2.get(pos))) {
                            pos++;
                        }
                        c1.add(i, p2.get(pos));
                        pos = 0;
                        while (c2.exists(p1.get(pos))) {
                            pos++;
                        }
                        c2.add(i, p1.get(pos));
                    }
                }
                c1.calculateFitness();
                c2.calculateFitness();
                newChromosomes.add(c1);
                newChromosomes.add(c2);
            } else {
                newChromosomes.add(p1);
                newChromosomes.add(p2);
            }
        }
        return newChromosomes;
    }

    public static ArrayList<Chromosome> partiallyMappedCrossover(ArrayList<Chromosome> chromosomeArrayList, double crossOverRate, Random generator) {
        ArrayList<Chromosome> newChromosomes = new ArrayList<>();
        int rate = Math.max((int)(10 - (10*crossOverRate)), 0);

        while (newChromosomes.size() != chromosomeArrayList.size()) {
            Chromosome p1 = chromosomeArrayList.get(generator.nextInt(chromosomeArrayList.size()-1));
            Chromosome p2 = chromosomeArrayList.get(generator.nextInt(chromosomeArrayList.size()-1));
            Chromosome c1 = new Chromosome(p1.size);
            Chromosome c2 = new Chromosome(p2.size);

            // It includes 0 and excludes the bound thus the +1
            if (generator.nextInt(10)+1 > rate) {
                int random1 = generator.nextInt(chromosomeArrayList.size()-1);
                int random2 = generator.nextInt(chromosomeArrayList.size()-1);

               int start = Math.min(random1, random2);
               int end = Math.max(random1, random2);

                for (int i = start; i <= end; i++) {
                    c1.add(i, p2.get(i));
                    c2.add(i, p1.get(i));
                }

                for (int i = 0; i < p1.size; i++) {
                    if (!c1.exists(p1.get(i))) {
                        c1.add(i, p1.get(i));;
                    }
                    if (!c2.exists(p2.get(i))) {
                        c2.add(i, p2.get(i));;
                    }
                }

                for (int j = 0; j < p1.size; j++) {
                    if (!c1.exists(p2.get(j))) {
                        c1.add(j, p2.get(j));;
                    }
                    if (!c2.exists(p1.get(j))) {
                        c2.add(j, p1.get(j));;
                    }
                }

                c1.calculateFitness();
                c2.calculateFitness();

                newChromosomes.add(c1);
                newChromosomes.add(c2);
            } else {
                newChromosomes.add(p1);
                newChromosomes.add(p2);
            }
        }
        return newChromosomes;
    }
}





























