package collections.ints.assertions;

import collections.ints.Set;

import java.util.Arrays;

public class BinaryProperty {

    private final Property property;
    private final int[] subject;
    private final int[] operand;

    private BinaryProperty(Property property, int[] subject, int[] operand) {
        this.property = property;
        this.subject = subject;
        this.operand = operand;
    }

    static BinaryStatement intersects(int[] subject, int[] operand) {
        return Property.INTERSECTS.holds(subject, operand);
    }

    static BinaryStatement doesNotIntersect(int[] subject, int[] operand) {
        return Property.INTERSECTS.doesNotHold(subject, operand);
    }

    static BinaryStatement equivalent(int[] subject, int[] operand) {
        return Property.EQUIVALENT.holds(subject, operand);
    }

    static BinaryStatement notEquivalent(int[] subject, int[] operand) {
        return Property.EQUIVALENT.doesNotHold(subject, operand);
    }

    static BinaryStatement subset(int[] subject, int[] operand) {
        return Property.SUBSET.holds(subject, operand);
    }

    static BinaryStatement noSubSet(int[] subject, int[] operand) {
        return Property.SUBSET.doesNotHold(subject, operand);
    }

    private <S extends Set<S>> boolean execute(SetFactory<S> setFactory) {
        return property.holds(setFactory, subject, operand);
    }

    private enum Property {

        INTERSECTS {
            @Override
            <S extends Set<S>> boolean holds(S subjectSet, S operandSet) {
                return subjectSet.intersects(operandSet);
            }
        },
        SUBSET {
            @Override
            <S extends Set<S>> boolean holds(S subjectSet, S operandSet) {
                return subjectSet.subSet(operandSet);
            }
        },
        EQUIVALENT {
            @Override
            <S extends Set<S>> boolean holds(S subjectSet, S operandSet) {
                return subjectSet.equivalent(operandSet);
            }
        };

        private <S extends Set<S>> BinaryStatement holds(int[] subject, int[] operand) {
            BinaryProperty property = new BinaryProperty(this, subject, operand);
            return BinaryStatement.holds(property::execute);
        }

        private <S extends Set<S>> BinaryStatement doesNotHold(int[] subject, int[] operand) {
            BinaryProperty property = new BinaryProperty(this, subject, operand);
            return BinaryStatement.doesNotHold(property::execute);
        }

        private <S extends Set<S>> boolean holds(SetFactory<S> setFactory, int[] subject, int[] operand) {
            S subjectSet = setFactory.create(subject);
            S operandSet = setFactory.create(operand);
            return holds(subjectSet, operandSet);
        }

        abstract <S extends Set<S>> boolean holds(S subjectSet, S operandSet);
    }
}
