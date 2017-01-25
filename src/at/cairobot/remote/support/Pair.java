/*
 * 
 */
package at.cairobot.remote.support;

/**
 *
 * @author redxef
 * @param <A>
 * @param <B>
 */
public class Pair<A, B> {

        private final A _0;
        private final B _1;

        public Pair(A first, B second)
        {
                _0 = first;
                _1 = second;
        }

        @Override
        public int hashCode()
        {
                int h0 = _0 != null ? _0.hashCode() : 0;
                int h1 = _1 != null ? _1.hashCode() : 0;

                return (h0 + h1) * h1 + h0;
        }

        @Override
        public boolean equals(Object oth)
        {
                if (!(oth instanceof Pair))
                        return false;
                Pair o = (Pair) oth;
                return ((_0 == o._0 || (_0 != null && o._0 != null && _0.equals(o._0)))
                        && (_1 == o._1 || (_1 != null && o._1 != null && _1.equals(o._1))));
        }

        @Override
        public String toString()
        {
                StringBuilder sb = new StringBuilder();
                sb.append('(');
                sb.append(_0.toString());
                sb.append(", ");
                sb.append(_1.toString());
                sb.append(')');
                return sb.toString();
        }

        public A getFirst()
        {
                return _0;
        }

        public B getSecond()
        {
                return _1;
        }
}
