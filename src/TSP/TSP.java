package TSP;

import Chromosome.Chromosome;
import City.City;
import City.ReadCities;
import GeneticUtilities.Crossover;
import GeneticUtilities.Mutation;
import GeneticUtilities.Tournament;

import java.io.*;
import java.util.*;

public class TSP {

    public static void GA(long seed, int popSize, int k, int maxGen, String crossOver, double crossOverRate, double mutationRate, int runs) throws Exception {
        Random generator = new Random(seed);
        ArrayList<Chromosome> chromosomeArray = new ArrayList<>();
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<City> temp;
        double bestFitnessGeneration = Double.MAX_VALUE;
        double averageFitnessGeneration = 0.0;
        double totalAverageBestFitness = 0.0;
        double totalAverageFitness = 0.0;
        Chromosome absoluteBestChromosome = null;
        Chromosome bestChromosome;
        PrintWriter printer = null;

        if (popSize % 2 > 0) {
            popSize += 1;
        }

        try {
            printer = new PrintWriter(crossOver+"-"+crossOverRate+"-"+mutationRate+"-"+System.nanoTime()+".csv", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assert printer != null;
        printer.println("Seed,Pop Size,K-Value,Max Generations,Cross Over Type,Cross Over Rate,Mutation Rate");
        printer.println(seed+","+popSize+","+k+","+maxGen+","+crossOver+","+crossOverRate+","+mutationRate);
        printer.println();

        ReadCities input = new ReadCities();
        try {
            cities = input.readFile("");
        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(1);
        }

        absoluteBestChromosome = new Chromosome(cities);

        for (int l = 0; l < runs; l++) {
            chromosomeArray.clear();

            for (int i = 0; i < popSize; i++) {
                temp = (ArrayList<City>) cities.clone();
                Collections.shuffle(temp, generator);
                chromosomeArray.add(new Chromosome(temp));
            }

            printer.println("Generation,Average Best Fitness Generation,Average Fitness,Best Fitness");

            bestChromosome = chromosomeArray.get(0);

            for (int i = 0; i < maxGen; i++) {
                averageFitnessGeneration = 0.0;
                bestFitnessGeneration = Double.MAX_VALUE;
                //Do the tournament selection
                chromosomeArray = Tournament.tournament(chromosomeArray, k, generator);
                //Do the crossover
                switch (crossOver){
                    case "uniform":
                        chromosomeArray = Crossover.uniformCrossover(chromosomeArray, crossOverRate, generator);
                        break;
                    case "pmx":
                        chromosomeArray = Crossover.partiallyMappedCrossover(chromosomeArray, crossOverRate, generator);
                        break;
                    default:
                        throw new Exception("Entered an invalid crossover.");
                }
                //Do the mutation
                chromosomeArray = Mutation.mutate(chromosomeArray, mutationRate, generator);

                for (Chromosome chromosome : chromosomeArray) {
                    if (chromosome.fitness < bestChromosome.fitness) {
                        bestChromosome = chromosome;
                    }
                    if (chromosome.fitness < bestFitnessGeneration) {
                        bestFitnessGeneration = chromosome.fitness;
                    }
                    if (chromosome.fitness < absoluteBestChromosome.fitness) {
                        absoluteBestChromosome = chromosome;
                    }
                    averageFitnessGeneration += chromosome.fitness;
                }
                //I have removed this because it ended up really increasing fitness.
//                chromosomeArray.set(chromosomeArray.size()-1, bestChromosome);
                averageFitnessGeneration = averageFitnessGeneration/chromosomeArray.size();
                printer.println((i+1)+","+bestFitnessGeneration+","+averageFitnessGeneration+","+bestChromosome.fitness);
            }
            printer.println();
            printer.println("Run Best Fitness,Chromosome");
            printer.println(bestChromosome.fitness+","+bestChromosome.print());
            printer.println();

            totalAverageBestFitness += bestFitnessGeneration;
            totalAverageFitness += averageFitnessGeneration;

            //I got an email the day before it was due saying this needs to change per run
            //so I had to shoe horn it in.
            generator = new Random(System.nanoTime());
        }

        totalAverageBestFitness = totalAverageBestFitness/(runs);
        totalAverageFitness = totalAverageFitness/(runs);

        printer.println("Total Average Best Fitness,Total Average Fitness,Best Fitness Run,Best Chromosome");
        printer.println(totalAverageBestFitness+","+totalAverageFitness+","+absoluteBestChromosome.fitness+","+absoluteBestChromosome.print());
        printer.close();
    }
}
