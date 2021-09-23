// --== CS400 File Header Information ==--
// Name: Connor Phillips
// Email: cjphillips@wisc.edu
// Team: Red
// Role: Data Wrangler
// TA: Hang
// Lecturer: Florian
import java.util.ArrayList;
import java.io.Reader;
public interface BusReaderInterface
{
    public ArrayList<BusStop> readFiles(Reader stops, Reader routes);
}