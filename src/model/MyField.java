package model;


import common.Coord;
import common.ShootResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Vlasov Alexander
 * Date: 24.08.2014
 * Time: 23:26
 *
 * @author Alexander Vlasov
 */
public class MyField extends Field {

    public MyField(int width, int height) {
        super(width, height);
    }

    /**
     * проверяет, можно ли расположить корабль с заданными координатами на поле с расставленными ранее кораблями
     *
     * @param ship
     *
     * @return
     */
    public boolean canPlace(Ship ship) {
        if (!bounds.isInBounds(ship)) return false;
        for (Ship alreadyPlaced : ships) {
            if (ship.isCrossing(alreadyPlaced)) return false;
        }
        return true;
    }


    /**
     * Ячейки вдали от кораблей
     *
     * @return
     */
    private List<Coord> getFreeCoords() {
        List<Coord> res = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                res.add(new Coord(i, j));
            }
        }
        for (Ship ship : ships) {
            for (int i = 0; i < ship.getAroundCoords().length; i++) {
                Coord coord = ship.getAroundCoords()[i];
                res.remove(coord);

            }
        }
        return res;
    }

    public void place(List<Ship> ships) {
        for (Ship ship : ships) {
            place(ship);
        }
    }

    public ShootResult shoot(Coord coord) {
        ShootResult shootResult = getCell(coord).shoot();
        if (shootResult == ShootResult.KILLED) {
            addKilled();
        }
        return shootResult;
    }

    public List<Ship> getAliveShips() {
        return ships.stream().filter(Ship::isAlive).collect(Collectors.toList());
    }
}
