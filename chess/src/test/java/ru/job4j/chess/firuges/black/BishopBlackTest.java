package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopBlackTest {
    @Test
    void whenReturnCorrectPosition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell expected = Cell.C8;
        Cell actual = bishopBlack.position();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenReturnCorrectCopy() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell expectedPosition = Cell.D7;
        Figure newBishopBlack = bishopBlack.copy(expectedPosition);
        assertThat(newBishopBlack).isNotEqualTo(bishopBlack);
        assertThat(newBishopBlack.position()).isEqualTo(expectedPosition);
    }

    @Test
    void whenReturnCorrectWayFromC1ToG5() {
        Cell sourcePosition = Cell.C1;
        Cell destPosition = Cell.G5;
        BishopBlack bishopBlack = new BishopBlack(sourcePosition);
        Cell[] expectedPWay = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Cell[] actualWay = bishopBlack.way(destPosition);
        assertThat(actualWay).isEqualTo(expectedPWay);
    }

    @Test
    void whenReturnIncorrectWayFromC1ToG4() {
        Cell sourcePosition = Cell.C1;
        Cell destPosition = Cell.G4;
        BishopBlack bishopBlack = new BishopBlack(sourcePosition);
        assertThatThrownBy(() -> bishopBlack.way(destPosition))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage(
                        String.format("Could not move by diagonal from %s to %s",
                                sourcePosition,
                                destPosition)
                );
    }

    @Test
    void whenCellsLayOnDiagonal() {
        Cell sourcePosition = Cell.C8;
        Cell destPosition = Cell.D7;
        BishopBlack bishopBlack = new BishopBlack(sourcePosition);
        boolean actualWay = bishopBlack.isDiagonal(bishopBlack.position(), destPosition);
        assertThat(actualWay).isTrue();
    }

    @Test
    void whenCellsDontLayOnDiagonal() {
        Cell sourcePosition = Cell.C8;
        Cell destPosition = Cell.D8;
        BishopBlack bishopBlack = new BishopBlack(sourcePosition);
        boolean actualWay = bishopBlack.isDiagonal(bishopBlack.position(), destPosition);
        assertThat(actualWay).isFalse();
    }
}