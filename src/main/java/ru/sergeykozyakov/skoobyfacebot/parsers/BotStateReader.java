package ru.sergeykozyakov.skoobyfacebot.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import ru.sergeykozyakov.skoobyfacebot.entities.xml.Root;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.net.URL;

/**
 * XML helper class for reading the Bot States file and its parsing
 *
 * @author Sergey Kozyakov
 */
public class BotStateReader implements StateReader {
    /**
     * Event logger
     */
    private static Logger LOG = LoggerFactory.getLogger(BotStateReader.class.getName());

    /**
     * Instance of the class
     */
    private static BotStateReader instance;

    /**
     * Root XML element of the States file
     */
    private Root root;

    /**
     * Private singleton constructor
     *
     * @throws BotException if parsing error occurred
     */
    private BotStateReader() throws BotException {
        parse();
    }

    /**
     * Creates the new instance of BotStateReader class or returns the existing one
     *
     * @return new {@code BotStateReader} instance
     * @throws BotException if parsing error occurred
     */
    public static synchronized BotStateReader getInstance() throws BotException {
        if (instance == null) {
            instance = new BotStateReader();
        }

        return instance;
    }

    /**
     * Parses the States file
     *
     * @throws BotException if parsing error occurred
     */
    @Override
    public void parse() throws BotException {
        if (root != null) {
            throw new BotException("States XML file was already parsed");
        }

        String statesFileName = "states.xml";
        String xsdStatesFileName = "states.xsd";

        URL statesUrl = getClass().getClassLoader().getResource(statesFileName);
        URL xsdStatesUrl = getClass().getClassLoader().getResource(xsdStatesFileName);

        if (statesUrl == null) {
            throw new BotException("States XML file is not found");
        }

        if (xsdStatesUrl == null) {
            throw new BotException("States XSD file is not found");
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdStatesUrl.getFile()));

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);

            root = (Root)jaxbUnmarshaller.unmarshal(new File(statesUrl.getFile()));

            LOG.info("States XML file is parsed successfully!");
        } catch (SAXException | JAXBException e) {
            throw new BotException(e);
        }
    }

    /**
     * Returns the States file unserialized root element
     *
     * @return States file unserialized root element
     */
    @Override
    public Root getRoot() {
        return root;
    }
}
