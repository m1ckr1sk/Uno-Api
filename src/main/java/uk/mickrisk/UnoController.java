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
		UnoPlayer player = unoGame.getPlayer(name);
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
		return "All the cards";
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/join", method = RequestMethod.POST)
	public String join(@RequestParam(value = "name", required = false, defaultValue = "anonymous") String name) {
		String returnMessage = "";
		if (unoGame.canAddPlayer()) {
			UnoPlayer newPlayer = new UnoPlayer(name);
			unoGame.addPlayer(newPlayer);
			log.debug("Joined the game:" + newPlayer.getName());
			returnMessage = "[{\"message\": \"Joined the game:" + newPlayer.getName() + "\"}]";
		} else {
			log.debug("New player not added, the game is full");
			returnMessage = "[{\"message\":\"New player not added, the game is full\"}]"; 
		}
		return returnMessage;
	}

	@RequestMapping(value = "/deal", method = RequestMethod.GET)
	public String deal() {
		log.debug("Dealing the cards");
		unoGame.deal();
		return "Dealing the cards";
	}

}
