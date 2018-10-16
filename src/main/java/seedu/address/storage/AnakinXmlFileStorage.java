package seedu.address.storage;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.XmlUtil;

/**
 * Stores anakin data in an XML file
 */
public class AnakinXmlFileStorage {
    /**
     * Saves the given anakin data to the specified file.
     */
    public static void saveDataToFile(Path file, AnakinXmlSerializableAnakin anakin)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, anakin);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Returns address book in the file or an empty address book
     */
    public static AnakinXmlSerializableAnakin loadDataFromSaveFile(Path file) throws DataConversionException,
            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, AnakinXmlSerializableAnakin.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

}
