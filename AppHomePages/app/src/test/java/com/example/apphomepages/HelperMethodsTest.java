package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.HelperMethods.HelperMethods;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelperMethodsTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    //used https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
    public void generateRandomArrayIndexWrong()
    {
        exception.expect(IndexOutOfBoundsException.class);
        ArrayList<Integer> randomArray = HelperMethods.generateRandomArray(new Random(), -10);
    }

    @Test
    //Used https://stackoverflow.com/questions/9712648/in-a-java-unit-test-how-do-i-assert-a-number-is-within-a-given-range
    public void generateRandomArray()
    {
        ArrayList<Integer> randomArray = HelperMethods.generateRandomArray(new Random(), 10);

        for (Integer i : randomArray)
            assertTrue(-99 <= i && i <= 99);
    }

    @Test
    public void generateTree()
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(0);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        Graph<Integer> integerGraph = HelperMethods.generateGraph(true, numbers);
        ArrayList<Node<Integer>> nodes = integerGraph.getGraphElements();

        Assert.assertEquals(new Integer(1), nodes.get(0).getAdjacentNodes().get(0).getNodeValue());
        Assert.assertEquals(new Integer(2), nodes.get(0).getAdjacentNodes().get(1).getNodeValue());

        Assert.assertEquals(new Integer(0), nodes.get(1).getAdjacentNodes().get(0).getNodeValue());
        Assert.assertEquals(new Integer(3), nodes.get(1).getAdjacentNodes().get(1).getNodeValue());
        Assert.assertEquals(new Integer(4), nodes.get(1).getAdjacentNodes().get(2).getNodeValue());

        Assert.assertEquals(new Integer(0), nodes.get(2).getAdjacentNodes().get(0).getNodeValue());
        Assert.assertEquals(new Integer(5), nodes.get(2).getAdjacentNodes().get(1).getNodeValue());
        Assert.assertEquals(new Integer(6), nodes.get(2).getAdjacentNodes().get(2).getNodeValue());

        Assert.assertEquals(new Integer(1), nodes.get(3).getAdjacentNodes().get(0).getNodeValue());

        Assert.assertEquals(new Integer(1), nodes.get(4).getAdjacentNodes().get(0).getNodeValue());

        Assert.assertEquals(new Integer(2), nodes.get(5).getAdjacentNodes().get(0).getNodeValue());

        Assert.assertEquals(new Integer(2), nodes.get(6).getAdjacentNodes().get(0).getNodeValue());
    }

    @Test
    public void generateGraph()
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(0);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        Graph<Integer> integerGraph = HelperMethods.generateGraph(false, numbers);
        ArrayList<Node<Integer>> nodes = integerGraph.getGraphElements();

        //check all values ended up on nodes...
        for (Node<Integer> node : nodes)
        {
            Assert.assertTrue(numbers.contains(node.getNodeValue()));
        }

        //check an edge goes into each value...(graph is connected!)
        for (Node<Integer> n : nodes)
        {
            boolean atLeastOne = false;

            for (Node<Integer> node : nodes)
            {
                if (!n.getNodeId().equals(node.getNodeId()) && node.getAdjacentNodes().contains(n))
                {
                    atLeastOne = true;
                }
            }

            Assert.assertTrue(atLeastOne);
        }
    }

    @Test
    public void convertToStrings()
    {
        ArrayList<Integer> randomArray = HelperMethods.generateRandomArray(new Random(), 10);
        ArrayList<String> randomArrayStrings = HelperMethods.convertToStrings(randomArray);

        for (int i = 0; i < randomArray.size(); i++)
            assertEquals(randomArray.get(i).toString(), randomArrayStrings.get(i));
    }

    @Test
    public void convertToArray()
    {
        ArrayList<Integer> randomArray = HelperMethods.generateRandomArray(new Random(), 10);
        Integer[] randomArrayAnswer = HelperMethods.convertToArray(randomArray);

        for (int i = 0; i < randomArray.size(); i++)
            assertEquals(randomArray.get(i), randomArrayAnswer[i]);
    }

    @Test
    public void convertFromArray()
    {
        Integer[] arr = {12, 14, 122, -10};
        ArrayList<Integer> arrayList = HelperMethods.convertFromArray(arr);

        for (int i = 0; i < arr.length; i++)
            assertEquals(new Integer(arr[i]), arrayList.get(i));
    }

    @Test
    public void testGenerateSetArray()
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(8);
        numbers.add(5);
        numbers.add(2);
        numbers.add(6);
        numbers.add(9);
        numbers.add(3);
        numbers.add(1);
        numbers.add(4);
        numbers.add(0);
        numbers.add(7);

        ArrayList<Integer> numbersGenerated = generateSetArray();

        for (Integer i : numbers)
            assertEquals(numbers.get(i), numbersGenerated.get(i));
    }

    //Test Helper Methods
    //Method for testing against Wikipedia visualizations
    private static ArrayList<Integer> generateSetArray()
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(8);
        numbers.add(5);
        numbers.add(2);
        numbers.add(6);
        numbers.add(9);
        numbers.add(3);
        numbers.add(1);
        numbers.add(4);
        numbers.add(0);
        numbers.add(7);
        return numbers;
    }
}