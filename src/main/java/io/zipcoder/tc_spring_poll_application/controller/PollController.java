package io.zipcoder.tc_spring_poll_application.controller;

import com.sun.deploy.net.HttpResponse;
import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PollController {

    private PollRepository pollRepository;

    @Autowired
    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody @Valid Poll poll) {

        poll = pollRepository.save(poll);
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();

        return new ResponseEntity<>(newPollUri, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);

    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        try{
        Poll p = pollRepository.findOne(pollId);
        return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<HttpResponse>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody @Valid Poll poll, @PathVariable Long pollId) {
        // Save the entity
        try{
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<HttpResponse>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        try {
            pollRepository.delete(pollId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<HttpResponse>(HttpStatus.NOT_FOUND);
        }
    }

    public void verifyPoll(Long pollid) throws ResourceNotFoundException {
        if (!pollRepository.exists(pollid)) {
            throw new ResourceNotFoundException();
        }
    }
}

