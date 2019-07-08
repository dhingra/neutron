/*package com.rd.neutron.client.jms;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.InvalidMessage;
import quickfix.MessageUtils;
import quickfix.UnsupportedMessageType;
*//**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 *//*
public class NeutronFIXMessageListener implements MessageListener {
   

        //private FixMessageProcessor fixMessageProcessor;

        public NeutronFIXMessageListener() {
           
        }

        @Override
        public void onMessage(Message message) {
            if (message instanceof TextMessage) {
                String messageText = null;
                try {
                    messageText = ((TextMessage)message).getText();
                    quickfix.Message fixMessage =
                        MessageUtils.parse(
                                FixUtil.getDefaultMessageFactory(),
                                FixUtil.getDefaultDataDictionary(),
                                messageText);
                    logger.debug("Received message:\n{}", FixFormatter.format(fixMessage));
                    fixMessageProcessor.fromApp(fixMessage, null);
                }
                catch (InvalidMessage e) {
                    logger.error("Invalid FIX message received: " + messageText, e);
                }
                catch (FieldNotFound e) {
                    logger.error("Invalid FIX message received: " + messageText, e);
                }
                catch (IncorrectDataFormat e) {
                    logger.error("Invalid FIX message received: " + messageText, e);
                }
                catch (IncorrectTagValue e) {
                    logger.error("Invalid FIX message received: " + messageText, e);
                }
                catch (UnsupportedMessageType e) {
                    logger.error("Invalid FIX message received: " + messageText, e);
                }
                catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

*/