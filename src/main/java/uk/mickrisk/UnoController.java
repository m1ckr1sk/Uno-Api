package uk.mickrisk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class UnoController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	UnoGame unoGame = new UnoGame();
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

	@RequestMapping(value = "/cards", method = RequestMethod.GET)
	public String cards(@RequestParam(value = "name", required = true) String name) {
		log.info("Getting the cards");
		UnoPlayer player = unoGame.getPlayer(name);
		return playerToJson(player);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/join", method = RequestMethod.POST)
	public String join(@RequestParam(value = "name", required = false, defaultValue = "anonymous") String name) {
		log.info(name + " is joining the game");
		UnoPlayer newPlayer = new UnoPlayer(name);
		return messageToJson(unoGame.addPlayer(newPlayer));
	}

	@RequestMapping(value = "/deal", method = RequestMethod.GET)
	public String deal() {
		log.info("Dealing the cards");
		unoGame.deal();
		return messageToJson("Dealing the cards");
	}
	
	private String messageToJson(String message)
	{
		UnoMessage unoMessage = new UnoMessage(message);
		String returnMessage = "{}";
		try {
			returnMessage = ow.writeValueAsString(unoMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return returnMessage;
	}
	
	private String playerToJson(UnoPlayer player)
	{
		if(player != null)
		{			
			String json = "";
			try {
				json = ow.writeValueAsString(player);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return json;
		}
		return messageToJson("Player could not be found");
	}

}
