/*
 * MRCP4J - Java API implementation of MRCPv2 specification
 *
 * Copyright (C) 2005-2006 SpeechForge - http://www.speechforge.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 * Contact: ngodfredsen@users.sourceforge.net
 *
 */
package org.mrcp4j.message.header;

/**
 * Abstract {@link org.mrcp4j.message.header.ValueFactory} providing basic implementation for all methods other than the main object creation method.
 *
 * @author Niels Godfredsen {@literal <}<a href="mailto:ngodfredsen@users.sourceforge.net">ngodfredsen@users.sourceforge.net</a>{@literal >}
 */
public abstract class BaseValueFactory implements ValueFactory {

    private Class<?> _valueClass;

    protected BaseValueFactory(Class<?> valueClass) {
        _valueClass = valueClass;
    }

    /**
     * Subclasses should override this method if there is any limitation on the valid values of the objects generated by this factory.
     * @param valueObject object to be validated.
     * @throws IllegalValueException if the value of the object passed is not valid for this factory.
     */
    protected void validateObject(Object valueObject) throws IllegalValueException {
        return;
    }

    /* (non-Javadoc)
     * @see org.mrcp4j.message.header.ValueFactory#toValueString(java.lang.Object)
     */
    public String toValueString(Object valueObject) throws ClassCastException, IllegalArgumentException {
        if (!getValueClass().isAssignableFrom(valueObject.getClass())) {
            throw new ClassCastException("Illegal type for valueObject (expected " +
                    getValueClass() + "): " + valueObject.getClass());
        }

        try {
            validateObject(valueObject);
        } catch (IllegalValueException e) {
            throw new IllegalArgumentException(e);
        }

        return valueObject.toString();
    }

    /* (non-Javadoc)
     * @see org.mrcp4j.message.header.ValueFactory#getValueClass()
     */
    public Class<?> getValueClass() {
        return _valueClass;
    }

}