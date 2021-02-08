/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.ClientCore;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 *
 * @author John
 */
public class AI {

    public final static int EASY = 4;
    public final static int MEDIUM = 3;
    public final static int HARD = 2;
    public final static int IMPOSSIBLE = 1;

    final Random gen;
    int maxConsider = AI.IMPOSSIBLE;

    public AI(int difficulty) {
        gen = new Random();
        maxConsider = difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.maxConsider = difficulty;
    }

    private DoubleStream scores(Board position, int player, int move, int depth) {
        try {
            position.take(move);
        } catch (AlreadyTakenException e) {
            return DoubleStream.of(0);
        }

        if (position.gameEnded()) {
            double complexityPenalty = 1.0 / Math.pow(depth, 2);

            if (position.getWinner() == -1) {
                return DoubleStream.of(0.8 * complexityPenalty);
            } else if (position.getWinner() != player) {
                return DoubleStream.of(-1 * complexityPenalty);
            } else {
                return DoubleStream.of(1 * complexityPenalty);
            }
        } else {
            return legalMoves(position).boxed().flatMapToDouble(i
                    -> scores(position.copy(), player, i, depth + 1)
            );
        }
    }

    public IntStream legalMoves(Board position) {
        return IntStream.range(0, 9).filter(i -> !position.taken(i));
    }

    public double value(Board position, int move) {
        return scores(position.copy(), position.nextPlayer(), move, 1).average().orElse(0.0);
    }

    public int play(Board board) {
        double[] values = IntStream.range(0, 9).mapToDouble(i -> value(board, i)).toArray();

        class ValueComparator implements Comparator<Integer> {

            @Override
            public int compare(Integer o1, Integer o2) {
                return -Double.compare(values[o1], values[o2]);
            }
        }

        PriorityQueue<Integer> bestMoves = legalMoves(board).boxed().collect(Collectors.toCollection(() -> new PriorityQueue<>(9, new ValueComparator())));
        
        if(IntStream.of(board.getHistory()).filter(value -> value>-1).count()==3 && board.getHistory()[2]%2==0 && board.nextPlayer()==1 && this.maxConsider == IMPOSSIBLE){
            bestMoves.stream().filter(move -> move%2==0).forEach(move -> values[move]/=2);
            bestMoves = legalMoves(board).boxed().collect(Collectors.toCollection(() -> new PriorityQueue<>(9, new ValueComparator())));
        }
        
        int prevMove = bestMoves.peek();

        int i = 0, take = 0;
        for (int move : bestMoves) {
            if (values[move] < values[prevMove] - 0.0001) {
                System.out.println(move);
                i++;
                if (i >= maxConsider) {
                    break;
                }
            }
            take++;
        }
        List<Integer> candidates = bestMoves.stream().limit(take).collect(Collectors.toList());
        return candidates.get(gen.nextInt(candidates.size()));
    }

}
