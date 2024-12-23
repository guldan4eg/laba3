import javax.swing.table.AbstractTableModel;
@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// В данной модели два столбца
        return 3;
    }
    public int getRowCount() {
        // Вычислить количество точек между началом и концом отрезка
        // исходя из шага табулирования
        return (int) Math.ceil((to - from) / step) + 1;
    }
    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;
        Double result = coefficients[0];
        for (int i = 1; i < coefficients.length; i++) {
            result = result * x + coefficients[i];
        }
        if (col == 0) {
            // Если запрашивается значение 1-го столбца, то это X
            return x;
        } else if (col ==1){
            // Если запрашивается значение 2-го столбца, то это значение
            // многочлена, вычисленное по схеме Горнера
            return result;
        }
        else
        {
            Boolean res = false;
            if((result.intValue())%2 != 0 && result.intValue()%3 != 0 && result.intValue()%5 != 0 && result.intValue()%7 != 0)
                res = true;
            else if(result.intValue() ==2 || result.intValue() ==3 || result.intValue() ==5 || result.intValue()==7)
                res = true;
            return res;
        }
    }
    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
// Название 2-го столбца
                return "Значение многочлена";
            default:
                return "Значение простое?";
        }
    }
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 2:
                return Boolean.class;
            default:
                return Double.class;
        }

    }
}
