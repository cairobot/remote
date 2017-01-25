/*
 * 
 */
package at.cairobot.remote.support;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

/**
 *
 * @author redxef
 */
public class PromptJTextField extends JTextField {

        private final Color original_color;
        private final Color prompt_color;
        private final String prompt;
        private boolean is_prompt;

        public PromptJTextField(String s)
        {
                super();

                original_color = super.getForeground();
                prompt_color = Color.gray;
                prompt = s;

                super.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e)
                        {
                                if (is_prompt) {
                                        is_prompt = false;
                                        setForeground(original_color);
                                        setText("");
                                }
                        }

                        @Override
                        public void focusLost(FocusEvent e)
                        {
                                if (getText().isEmpty()) {
                                        is_prompt = true;
                                        setForeground(prompt_color);
                                        setText(prompt);
                                }
                        }
                });
                is_prompt = true;
                super.setForeground(prompt_color);
                setText(prompt);
        }

        @Override
        public void setText(String s)
        {
                super.setText(s);
//                if (s == null || s.isEmpty()) {
//                        is_prompt = true;
//                        setForeground(prompt_color);
//                        super.setText(prompt);
//                } else {
//                        is_prompt = false;
//                        setForeground(original_color);
//                        super.setText(s);
//                }
        }
}
